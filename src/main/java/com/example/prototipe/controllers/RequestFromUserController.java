package com.example.prototipe.controllers;


import com.example.prototipe.common.utils.ValidationException;
import com.example.prototipe.dtos.KeyDto;
import com.example.prototipe.dtos.RequestFromUserDto;
import com.example.prototipe.services.RequestFromUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("request/from/user")
public class RequestFromUserController {

    private final RequestFromUserService requestFromUserService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RequestFromUserDto> getRequestFromUserByRequestId(
            @PathVariable Long id) throws ValidationException {
        return ResponseEntity.ok(requestFromUserService.getRequestFromUser(id));
    }

    @GetMapping(value = "/user/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RequestFromUserDto> getRequestFromUserByUserId(
            @PathVariable Long userId) throws ValidationException {
        return ResponseEntity.ok(requestFromUserService.getRequestFromUserByUserId(userId));
    }

    @GetMapping(value = "/headman/{headmanId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RequestFromUserDto>> getAllRequestForHeadman(
            @PathVariable Long headmanId) throws ValidationException{
        return ResponseEntity.ok(requestFromUserService.
                getRequestsFromUsersForHeadmanByHeadmanId(headmanId));
    }

    @PostMapping(value = "start/creating/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KeyDto> startCreatingRequestFromUser(
            @PathVariable Long userId) throws Exception{
        return ResponseEntity.ok(requestFromUserService.createKeyByRequest(userId));
    }

    @PostMapping(value = "/create/{userId}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Object>> createRequest(@PathVariable Long userId,
                          @RequestBody RequestFromUserDto requestFromUserDto)
            throws ValidationException{
        try {
            requestFromUserService.createRequestFromUser(userId, requestFromUserDto);
            List<Object> ret = new ArrayList<Object>();
            ret.add(true);
            ret.add("");
            return ResponseEntity.ok(ret);
        }
        catch (Exception e){
            List<Object> ret = new ArrayList<Object>();
            ret.add(false);
            ret.add(e.getMessage());
            return ResponseEntity.ok(ret);
        }
    }


}
