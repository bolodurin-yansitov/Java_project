package com.example.prototipe.services;

import com.example.prototipe.common.utils.ValidationException;
import com.example.prototipe.daos.TimeOfRequestingDao;
import com.example.prototipe.dtos.TimeOfRequestingDto;
import com.example.prototipe.dtos.UsersDto;
import com.example.prototipe.entities.TimeOfRequesting;
import com.example.prototipe.entities.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.prototipe.common.utils.ValidationUtils.*;

@Service
@RequiredArgsConstructor
public class TimeOfRequestingService {
    private final TimeOfRequestingDao timeDao;

    public TimeOfRequestingDto getTimeOfRequesting(Long headmanId)
            throws ValidationException {
        validateIsNull(headmanId, "Headman's id is not provide");

        TimeOfRequesting timeOfRequesting = timeDao.
                findTimeOfRequestingByHeadmanId(headmanId);
        validateIsNull(timeOfRequesting, "No time of requesting which " +
                "created such by headman with such id or no headman with " +
                "such id" + headmanId);

        return buildTimeOfRequestingDtoFromTimeOfRequesting(timeOfRequesting);
    }

    private TimeOfRequestingDto buildTimeOfRequestingDtoFromTimeOfRequesting(
            TimeOfRequesting timeOfRequesting){
        return new TimeOfRequestingDto(
                timeOfRequesting.getStartOfSubmission(),
                timeOfRequesting.getEndOfSubmission(),
                buildHeadmanDtoFromHeadman(timeOfRequesting.getUser()));
    }

    private UsersDto buildHeadmanDtoFromHeadman(Users headman){
        return new UsersDto(
                headman.getId(), headman.getRole(),
                headman.getFname(), headman.getSname(),
                headman.getFaculNum(), headman.getCourseNum(),
                headman.getEmail());
    }

    public TimeOfRequestingDto getTimeOfRequestingByUserId(Long userId)
            throws ValidationException {
        validateIsNull(userId, "User id is not provide");

        Users headman = timeDao.findHeadmanOfUserByUserId(userId);
        validateIsNull(headman, "ERROR in finding headman or " +
                "no user with such id " + userId);

        TimeOfRequesting timeOfRequesting = timeDao.
                findTimeOfRequestingByHeadmanId(headman.getId());
        validateIsNull(timeOfRequesting, "No time of requesting, created by " +
                "this headman with such id " + headman.getId());

        return buildTimeOfRequestingDtoFromTimeOfRequesting(timeOfRequesting);
    }


}
