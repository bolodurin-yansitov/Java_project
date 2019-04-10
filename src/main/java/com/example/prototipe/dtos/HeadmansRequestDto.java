package com.example.prototipe.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;


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
    byte[] file;

    @Getter
    @Setter
    @NotNull
    private Long headmanId;

    @Getter
    @Setter
    private Date timeOfRequest;

    @Getter
    @Setter
    private UsersDto headmanDto;

    public HeadmansRequestDto(Long requestId, byte[] file,
                              Long headmanId, Date timeOfRequest,
                              UsersDto user){
        this.requestId = requestId;
        this.file = file;
        this.headmanId = headmanId;
        this.timeOfRequest = timeOfRequest;
        this.headmanDto = user;
    }

    public HeadmansRequestDto(Long requestId, Long headmanId,
                              Date timeOfRequest, UsersDto user){
        this.requestId = requestId;
        this.headmanId = headmanId;
        this.timeOfRequest = timeOfRequest;
        this.headmanDto = user;
    }
}
