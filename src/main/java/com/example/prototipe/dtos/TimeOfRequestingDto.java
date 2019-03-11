package com.example.prototipe.dtos;

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

    public TimeOfRequestingDto(Date startOfSubmission, Date endOfSubmission,
                               Long headmanId){
        this.startOfSubmission = startOfSubmission;
        this.endOfSubmission = endOfSubmission;
        this.headmanId = headmanId;
    }

}
