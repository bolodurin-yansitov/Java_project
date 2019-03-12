package com.example.prototipe.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class KeyDto {
    @Getter
    @Setter
    @NotNull
    private Long openKey;

    @Getter
    @Setter
    @NotNull
    private Long userId;

    @Getter
    @Setter
    @NotNull
    private Date creatingTime;

    @Getter
    @Setter
    @NotNull
    private Date deletingTime;

    public KeyDto(Long openKey, Long userId,
                  Date creatingTime, Date deletingTime){
        this.openKey = openKey;
        this.userId = userId;
        this.creatingTime = creatingTime;
        this.deletingTime = deletingTime;
    }
}
