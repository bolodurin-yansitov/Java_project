package com.example.prototipe.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "TIME_OF_REQUESTING")
@ToString
public class TimeOfRequesting {
    @Column(name = "START_OF_SUBMISSION")
    @Getter
    @Setter
    @NotNull
    private Date startOfSubmission;

    @Column(name = "END_OF_SUBMISSION")
    @Getter
    @Setter
    @NotNull
    private Date endOfSubmission;


}
