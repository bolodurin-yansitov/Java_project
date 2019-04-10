package com.example.prototipe.services;

import com.example.prototipe.common.utils.ValidationException;
import com.example.prototipe.daos.RequestFromUserDao;
import com.example.prototipe.dtos.KeyDto;
import com.example.prototipe.dtos.RequestFromUserDto;
import com.example.prototipe.dtos.UsersDto;
import com.example.prototipe.entities.Key;
import com.example.prototipe.entities.RequestFromUser;
import com.example.prototipe.entities.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.prototipe.common.utils.ValidationUtils.*;
import static com.example.prototipe.entities.enums.StatusOfRequest.SENT;

@Service
@RequiredArgsConstructor
public class RequestFromUserService {
    private final RequestFromUserDao requestFromUserDao;

    public RequestFromUserDto getRequestFromUser(Long id)
            throws ValidationException {
        validateIsNull(id, "Request id is not provide");

        RequestFromUser requestFromUser = requestFromUserDao.findOne(id);
        validateIsNull(requestFromUser, "No request from user with such id " +
                id);

        return buildRequestFromUserDtoFromRequestFromUser(requestFromUser);
    }

    private RequestFromUserDto buildRequestFromUserDtoFromRequestFromUser(
            RequestFromUser requestFromUser) {
        return new RequestFromUserDto(
                requestFromUser.getRequestId(),
                requestFromUser.getTimeOfRequesting(),
                requestFromUser.getStatusOfRequest(),
                requestFromUser.getReasonOfRequesting(),
                buildKeyDtoFromKey(requestFromUser.getKey()),
                buildUserDtoFromUser(requestFromUser.getUser()));
    }

    private UsersDto buildUserDtoFromUser(Users user) {
        return new UsersDto(
                user.getId(), user.getRole(), user.getFname(),
                user.getSname(), user.getFaculNum(), user.getCourseNum(),
                user.getEmail());
    }

    private KeyDto buildKeyDtoFromKey(Key key) {
        return new KeyDto(
                key.getOpenKey(), key.getUserId(),
                key.getCreatingTime(), key.getDeletingTime());
    }

    public RequestFromUserDto getRequestFromUserByUserId(Long userId)
            throws ValidationException {
        validateIsNull(userId, "User id is not provide");

        Users user = requestFromUserDao.findUserByUsersId(userId);
        validateIsNull(user, "No user with such user id " + userId);

        List<RequestFromUser> requestsFromUser = user.getRequestsFromUser();

        RequestFromUser lastRequestFromUser = new RequestFromUser();
        int count = 0;

        for(RequestFromUser j : requestsFromUser){
            if(j.getStatusOfRequest() == SENT){
                lastRequestFromUser = j;
                count++;
            }
        }

        if(count > 1){
            validateIsNull(null, "Quantity of sending requests from " +
                    "user is bigger one " + userId);
        }
        else if(count == 0){
            validateIsNull(null, "No sending requests from such user " +
                    userId);
        }

        return buildRequestFromUserDtoFromRequestFromUser(lastRequestFromUser);
    }
}
