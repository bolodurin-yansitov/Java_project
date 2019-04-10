package com.example.prototipe.controllers;


import com.example.prototipe.common.utils.ValidationException;
import com.example.prototipe.dtos.RequestFromUserDto;
import com.example.prototipe.services.RequestFromUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("request/from/user")
public class RequestFromUserController {

    private RequestFromUserService requestFromUserService;

    public RequestFromUserController(RequestFromUserService
                                             requestFromUserService){
        this.requestFromUserService = requestFromUserService;
    }

    @GetMapping("{id}")
    public RequestFromUserDto getRequestFromUserByRequestId(
            @PathVariable Long id) throws ValidationException {
        return requestFromUserService.getRequestFromUser(id);
    }

    @GetMapping("user/{userId}")
    public RequestFromUserDto getRequestFromUserByUserId(
            @PathVariable Long userId) throws ValidationException {
        return requestFromUserService.getRequestFromUserByUserId(userId);
    }
}
