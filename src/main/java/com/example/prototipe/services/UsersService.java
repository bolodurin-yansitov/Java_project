package com.example.prototipe.services;

import com.example.prototipe.daos.UsersDao;
import com.example.prototipe.dtos.UsersDto;
import com.example.prototipe.entities.Users;
import com.example.prototipe.common.utils.ValidationException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import static com.example.prototipe.common.utils.ValidationUtils.*;

@Service
@RequiredArgsConstructor
public class UsersService {
    private static final Logger log = LoggerFactory.getLogger(
            UsersService.class);

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

    public UsersDto getUserByEmail(String userEmail)
            throws ValidationException {
        validateIsNull(userEmail, "User email is not provide");

        Users user = this.usersDao.findByEmail(userEmail);
        validateIsNull(user, "No user with such email " + userEmail);

        return buildUsersDtoFromUser(user);
    }

    public Users createUser(UsersDto usersDto) throws ValidationException{
        validateIsNull(usersDto, "User DTO is not provide");
        validateIsNotNull(usersDto.getId(), "Can not create user with");

        validateIsNull(usersDto.getEmail(), "Email of user is not provide");
        validateIsNull(usersDto.getFname(), "First name of user is not provide");
        validateIsNull(usersDto.getSname(), "Second name of user is not provide");
        validateIsNull(usersDto.getGroupNum(), "Group number of user " +
                "is not provide");
        validateIsNull(usersDto.getCourseNum(), "Course number of user " +
                "is not provide");
        validateIsNull(usersDto.getFaculNum(), "Faculty number of user " +
                "is not provide");
        validateIsNull(usersDto.getRole(), "Role of user is not provide");

        if(usersDao.findByEmail(usersDto.getEmail()) == null)
        {
            Users user = buildUserFromUserDto(usersDto);
            this.usersDao.save(user);
            return user;
        }
        else
            return null;
    }

    private Users buildUserFromUserDto(UsersDto usersDto){
        return new Users(
                usersDto.getEmail(), usersDto.getPassword(), usersDto.getFname(),
                usersDto.getSname(), usersDto.getGroupNum(),
                usersDto.getCourseNum(), usersDto.getFaculNum(),
                usersDto.getRole());
    }
}
