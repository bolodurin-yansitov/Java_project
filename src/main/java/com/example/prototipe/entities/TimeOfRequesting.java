package com.example.prototipe.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "TIME_OF_REQUESTING")
@ToString(exclude = "user")
public class TimeOfRequesting {
    @Id
    @Column(name = "START_OF_SUBMISSION")
    @Getter
    @Setter
    @NotNull
    private LocalDateTime startOfSubmission;

    @Column(name = "END_OF_SUBMISSION")
    @Getter
    @Setter
    @NotNull
    private LocalDateTime endOfSubmission;

    @Column(name = "HEADMAN_ID")
    @Getter
    @Setter
    private Long headmanId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HEADMAN_ID", insertable = false, updatable = false)
    @Getter
    @Setter
    private Users user;
}
