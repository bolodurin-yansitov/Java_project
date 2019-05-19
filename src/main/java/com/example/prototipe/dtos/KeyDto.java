package com.example.prototipe.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class KeyDto {
    @Getter
    @Setter
    private byte[] openKey;

    @Getter
    @Setter
    @NotNull
    private Long userId;

    @Getter
    @Setter
    private LocalDateTime creatingTime;

    @Getter
    @Setter
    private LocalDateTime deletingTime;

    public KeyDto(byte[] openKey, Long userId,
                  LocalDateTime creatingTime, LocalDateTime deletingTime){
        this.openKey = openKey;
        this.userId = userId;
        this.creatingTime = creatingTime;
        this.deletingTime = deletingTime;
    }
}
