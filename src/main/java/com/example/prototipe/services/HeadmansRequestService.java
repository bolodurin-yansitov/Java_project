package com.example.prototipe.services;

import com.example.prototipe.common.utils.ValidationException;
import com.example.prototipe.daos.HeadmansRequestDao;
import com.example.prototipe.dtos.HeadmansRequestDto;
import com.example.prototipe.dtos.UsersDto;
import com.example.prototipe.entities.HeadmansRequest;
import com.example.prototipe.entities.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.prototipe.common.utils.ValidationUtils.validateIsNull;

@Service
@RequiredArgsConstructor
public class HeadmansRequestService {
    private final HeadmansRequestDao headmansRequestDao;


    public HeadmansRequestDto getHeadmansRequest(Long headmansRequestId)
                throws ValidationException {
        validateIsNull(headmansRequestId, "Headman's request id is not provide");

        HeadmansRequest headmansRequest = headmansRequestDao.findOne(headmansRequestId);
        validateIsNull(headmansRequest, "No headman's request for such id " +
                headmansRequest.getRequestId());

        return buildHeadmansRequestDtoFromHeadmansRequest(headmansRequest);
    }

    private HeadmansRequestDto buildHeadmansRequestDtoFromHeadmansRequest(
            HeadmansRequest headmansRequest){
        HeadmansRequestDto headmansRequestDto = new HeadmansRequestDto(
                headmansRequest.getRequestId(), headmansRequest.getHeadmanId(),
                headmansRequest.getTimeOfRequesting(),
                buildHeadmanDtoFromHeadman(headmansRequest.getHeadman())
        );

        return headmansRequestDto;
    }

    private UsersDto buildHeadmanDtoFromHeadman(Users headman) {
        return new UsersDto(headman.getId(), headman.getRole(),
                            headman.getFname(), headman.getSname(),
                            headman.getFaculNum(), headman.getCourseNum(),
                            headman.getEmail());
    }

    public HeadmansRequestDto getHeadmansRequestByHeadmanId(Long headmanId)
            throws ValidationException {
        validateIsNull(headmanId, "Headman's id is not provide");

        HeadmansRequest headmansRequest = headmansRequestDao.
                findHeadmansRequestByHeadmanId(headmanId);
        validateIsNull(headmansRequest, "No headman's request for such " +
                "headman id " + headmansRequest.getHeadmanId());

        return buildHeadmansRequestDtoFromHeadmansRequest(headmansRequest);
    }
}
