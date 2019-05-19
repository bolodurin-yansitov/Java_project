package com.example.prototipe.controllers;

import com.example.prototipe.common.utils.ValidationException;
import com.example.prototipe.dtos.HeadmansRequestDto;
import com.example.prototipe.services.HeadmansRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("headman/request")
public class HeadmanRequestController {
    private final HeadmansRequestService headmansRequestService;

    @GetMapping(value = "/{headmanId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public HeadmansRequestDto getHeadmansRequest(@PathVariable Long headmanId)
            throws ValidationException {
        return headmansRequestService.getHeadmansRequestByHeadmanId(headmanId);
    }

    @PostMapping(value = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Object>> createHeadmansRequest(
            @RequestBody HeadmansRequestDto headmansRequestDto)
            throws ValidationException{
        try {
            headmansRequestService.createHeadmansRequest(headmansRequestDto);
            List<Object> ret = new ArrayList<Object>();
            ret.add(true);
            ret.add("");
            return ResponseEntity.ok(ret);
        }
        catch (ValidationException e){
            List<Object> ret = new ArrayList<Object>();
            ret.add(false);
            ret.add(e.getMessage());
            return ResponseEntity.ok(ret);
        }
    }
}
