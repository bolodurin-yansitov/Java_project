package com.example.prototipe.controllers;

import com.example.prototipe.common.utils.ValidationException;
import com.example.prototipe.dtos.HeadmansRequestDto;
import com.example.prototipe.services.HeadmansRequestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("headman/request")
public class HeadmanRequestController {
    private HeadmansRequestService headmansRequestService;

    public HeadmanRequestController(HeadmansRequestService headmansRequestService){
        this.headmansRequestService = headmansRequestService;
    }

    @GetMapping("{headmanId}")
    public HeadmansRequestDto getHeadmansRequest(Long headmanId)
            throws ValidationException {
        return headmansRequestService.getHeadmansRequestByHeadmanId(headmanId);
    }
}
