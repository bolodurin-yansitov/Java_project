package com.example.prototipe.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class HeadmansRequestDto {
    @Getter
    @Setter
    private Long requestId;

    @Getter
    @Setter
    @NotNull
    byte[] file;

    @Getter
    @Setter
    @NotNull
    private Long headmanId;

    @Getter
    @Setter
    private LocalDateTime timeOfRequest;

    @Getter
    @Setter
    private UsersDto headmanDto;

    public HeadmansRequestDto(Long requestId, byte[] file,
                              Long headmanId, LocalDateTime timeOfRequest,
                              UsersDto user){
        this.requestId = requestId;
        this.file = file;
        this.headmanId = headmanId;
        this.timeOfRequest = timeOfRequest;
        this.headmanDto = user;
    }

    public HeadmansRequestDto(Long requestId, Long headmanId,
                              LocalDateTime timeOfRequest, UsersDto user){
        this.requestId = requestId;
        this.headmanId = headmanId;
        this.timeOfRequest = timeOfRequest;
        this.headmanDto = user;
    }
}
