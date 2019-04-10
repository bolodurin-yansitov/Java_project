package com.example.prototipe.dtos;

import com.example.prototipe.entities.Users;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class TimeOfRequestingDto {
    @Getter
    @Setter
    @NotNull
    private Date startOfSubmission;

    @Getter
    @Setter
    @NotNull
    private Date endOfSubmission;

    @Getter
    @Setter
    private Long headmanId;

    @Getter
    @Setter
    private UsersDto headmanCreator;

    public TimeOfRequestingDto(Date startOfSubmission, Date endOfSubmission,
                               Long headmanId){
        this.startOfSubmission = startOfSubmission;
        this.endOfSubmission = endOfSubmission;
        this.headmanId = headmanId;
    }

    public TimeOfRequestingDto(Date startOfSubmission, Date endOfSubmission,
                               UsersDto headmanCreator){
        this.startOfSubmission = startOfSubmission;
        this.endOfSubmission = endOfSubmission;
        this.headmanCreator = headmanCreator;
    }
}
