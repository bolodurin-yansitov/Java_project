package com.example.prototipe.controllers;

import com.example.prototipe.common.utils.ValidationException;
import com.example.prototipe.dtos.TimeOfRequestingDto;
import com.example.prototipe.services.TimeOfRequestingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("time")
public class TimeOfRequestingController {
    private TimeOfRequestingService timeOfRequestingService;

    public TimeOfRequestingController(TimeOfRequestingService
                                   timeOfRequestingService){
        this.timeOfRequestingService = timeOfRequestingService;
    }

    @GetMapping("user/{userId}")
    public TimeOfRequestingDto getTimeOfRequestingByUserId(
            @PathVariable Long userId) throws ValidationException {
        return timeOfRequestingService.getTimeOfRequestingByUserId(userId);
    }

    @GetMapping("{headmanId}")
    public TimeOfRequestingDto getTimeOfRequesting(@PathVariable Long headmanId)
            throws ValidationException {
        return timeOfRequestingService.getTimeOfRequesting(headmanId);
    }
}
