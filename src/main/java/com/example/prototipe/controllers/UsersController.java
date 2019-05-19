package com.example.prototipe.controllers;


import com.example.prototipe.common.utils.ValidationException;
import com.example.prototipe.dtos.UsersDto;
import com.example.prototipe.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UsersController {

    private final UsersService usersService;

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

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@RequestBody UsersDto usersDto)
            throws ValidationException{
        if(usersService.createUser(usersDto) == null)
            return ResponseEntity.ok("Error: user with such method is exist");
        else    return ResponseEntity.ok("Success!");
    }

    //fixme
    @GetMapping
    public void getRequestsForRegistration(){}
}
