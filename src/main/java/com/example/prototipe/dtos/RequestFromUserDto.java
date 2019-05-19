package com.example.prototipe.dtos;

import com.example.prototipe.entities.enums.ReasonOfRequesting;
import com.example.prototipe.entities.enums.StatusOfRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RequestFromUserDto {
    @Getter
    @Setter
    private Long requestId;

    @Getter
    @Setter
    @NotNull
    private byte[] file;

    @Getter
    @Setter
    @NotNull
    private byte[] signFile;

    @Getter
    @Setter
    @NotNull
    private byte[] proof;

    @Getter
    @Setter
    private Short statusOfRequestCode;

    @JsonIgnore
    public StatusOfRequest getStatusOfRequest(){
        return StatusOfRequest.parse(this.statusOfRequestCode);
    }

    @JsonIgnore
    public void setStatusOfRequest(StatusOfRequest statusOfRequest){
        this.statusOfRequestCode = statusOfRequest.getValue();
    }

    @Getter
    @Setter
    private Short reasonOfRequestingCode;

    @JsonIgnore
    public ReasonOfRequesting getReasonOfRequesting(){
        return ReasonOfRequesting.parse(this.reasonOfRequestingCode);
    }

    @JsonIgnore
    public void setReasonOfRequesting(ReasonOfRequesting reasonOfRequesting){
        this.reasonOfRequestingCode = reasonOfRequesting.getValue();
    }

    @Getter
    @Setter
    @NotNull
    private LocalDateTime timeOfRequesting;

    @Getter
    @Setter
    @NotNull
    private Long userId;

    @Getter
    @Setter
    private KeyDto key;

    @Getter
    @Setter
    private UsersDto user;

    public RequestFromUserDto(Long requestId, Long userId, LocalDateTime timeOfRequesting,
                              byte[] file, byte[] proof,
                              StatusOfRequest statusOfRequest,
                              ReasonOfRequesting reasonOfRequesting, KeyDto key,
                              UsersDto user){
        this.requestId = requestId;
        this.userId = userId;
        this.timeOfRequesting = timeOfRequesting;
        this.file = file;
        this.proof = proof;
        this.reasonOfRequestingCode = reasonOfRequesting.getValue();
        this.statusOfRequestCode = statusOfRequest.getValue();
        this.key = key;
        this.user = user;
    }

    public RequestFromUserDto(Long requestId, LocalDateTime timeOfRequesting,
                              StatusOfRequest statusOfRequest,
                              ReasonOfRequesting reasonOfRequesting, KeyDto key,
                              UsersDto user){
        this.requestId = requestId;
        this.timeOfRequesting = timeOfRequesting;
        this.reasonOfRequestingCode = reasonOfRequesting.getValue();
        this.statusOfRequestCode = statusOfRequest.getValue();
        this.key = key;
        this.user = user;
    }

    public RequestFromUserDto(Long requestId, LocalDateTime timeOfRequesting,
                              StatusOfRequest statusOfRequest,
                              ReasonOfRequesting reasonOfRequesting){
        this.requestId = requestId;
        this.timeOfRequesting = timeOfRequesting;
        this.reasonOfRequestingCode = reasonOfRequesting.getValue();
        this.statusOfRequestCode = statusOfRequest.getValue();
    }
}
