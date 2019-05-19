package com.example.prototipe.controllers;

import com.example.prototipe.common.utils.ValidationException;
import com.example.prototipe.dtos.TimeOfRequestingDto;
import com.example.prototipe.services.TimeOfRequestingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("time")
public class TimeOfRequestingController {
    private final TimeOfRequestingService timeOfRequestingService;

    @GetMapping(value = "/user/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public TimeOfRequestingDto getTimeOfRequestingByUserId(
            @PathVariable Long userId) throws ValidationException {
        return timeOfRequestingService.getTimeOfRequestingByUserId(userId);
    }

    @GetMapping("/{headmanId}")
    public TimeOfRequestingDto getTimeOfRequesting(@PathVariable Long headmanId)
            throws ValidationException {
        return timeOfRequestingService.getTimeOfRequesting(headmanId);
    }
}
