package com.example.prototipe.services;

import com.example.prototipe.daos.UsersDao;
import com.example.prototipe.dtos.UsersDto;
import com.example.prototipe.entities.Users;
import com.example.prototipe.common.utils.ValidationExseption;


import static com.example.prototipe.common.utils.ValidationUtils.validateIsNull;

public class UsersService {
    private UsersDao usersDao;

    public UsersService(UsersDao usersDao)
    {
        this.usersDao = usersDao;
    }

    public UsersDto getUser(Long userId) throws ValidationExseption{
        validateIsNull(userId, "User id is not provide");

        Users user = usersDao.findOne(userId);
        validateIsNull(user, "No user with user id" + userId);

        return buildUsersDtoFromUser(user);
    }

    private UsersDto buildUsersDtoFromUser(Users user){
        UsersDto usersDto = new UsersDto(user.getId(), user.getRole(),
                user.getFname(), user.getSname(), user.getGroupNum(),
                user.getFaculNum(), user.getCourseNum(), user.getEmail());
        /*usersDto.setId(user.getId());
        usersDto.setEmail(user.getEmail());
        usersDto.setFname(user.getFname());
        usersDto.setSname(user.getSname());
        usersDto.setCourseNum(user.getCourseNum());*/

        return usersDto;
    }
}
