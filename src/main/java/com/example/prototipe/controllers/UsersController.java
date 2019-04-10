package com.example.prototipe.controllers;


import com.example.prototipe.common.utils.ValidationException;
import com.example.prototipe.dtos.UsersDto;
import com.example.prototipe.services.UsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UsersController {

    private UsersService usersService;

    public UsersController(UsersService usersService){
        this.usersService = usersService;
    }

    @GetMapping("{userId}")
    public UsersDto getUser(@PathVariable Long userId)
            throws ValidationException {
        return this.usersService.getUser(userId);
    }

    @GetMapping("email/{email:\\w+@\\w+.\\w+}")
    public UsersDto getUserByEmail(@PathVariable String email)
            throws ValidationException {
        return usersService.getUserByEmail(email);
    }
}
