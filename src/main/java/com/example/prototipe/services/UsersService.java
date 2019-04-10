package com.example.prototipe.services;

import com.example.prototipe.daos.UsersDao;
import com.example.prototipe.dtos.UsersDto;
import com.example.prototipe.entities.Users;
import com.example.prototipe.common.utils.ValidationException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import static com.example.prototipe.common.utils.ValidationUtils.validateIsNull;

@Service
@RequiredArgsConstructor
public class UsersService {
    private static final Logger log = LoggerFactory.getLogger(UsersService.class);

    private final UsersDao usersDao;

    public UsersDto getUser(Long userId) throws ValidationException {
        validateIsNull(userId, "User id is not provide");

        Users user = usersDao.findOne(userId);
        validateIsNull(user, "No user with user id " + userId);

        return buildUsersDtoFromUser(user);
    }

    private UsersDto buildUsersDtoFromUser(Users user){
        UsersDto usersDto = new UsersDto(
                user.getId(), user.getRole(),
                user.getFname(), user.getSname(), user.getGroupNum(),
                user.getFaculNum(), user.getCourseNum(),
                user.getSummaryHelp(), user.getLastMonthHelp(), user.getEmail());

        return usersDto;
    }

    public UsersDto getUserByEmail(String userEmail) throws ValidationException {
        validateIsNull(userEmail, "User email is not provide");

        Users user = this.usersDao.findByEmail(userEmail);
        validateIsNull(user, "No user with such email " + userEmail);

        return buildUsersDtoFromUser(user);
    }
}
