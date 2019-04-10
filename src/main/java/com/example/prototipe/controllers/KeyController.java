package com.example.prototipe.controllers;

import com.example.prototipe.common.utils.ValidationException;
import com.example.prototipe.dtos.KeyDto;
import com.example.prototipe.services.KeyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("key")
public class KeyController {
    private KeyService keyService;

    public KeyController(KeyService keyService){
        this.keyService = keyService;
    }

    @GetMapping("{userId}")
    public KeyDto getKey(@PathVariable Long userId)
            throws ValidationException {
        return this.keyService.getKeyByUserId(userId);
    }
}
