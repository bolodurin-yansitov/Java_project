package com.example.prototipe.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "KEY")
@ToString
public class Key {
    @Column(name = "CLOSE_KEY")
    @Getter
    @Setter
    @NotNull
    private Long close_key;

    @Column(name = "OPEN_KEY")
    @Getter
    @Setter
    @NotNull
    private Long open_key;

    @Column(name = "USER_ID")
    @Getter
    @Setter
    @NotNull
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @Getter
    @Setter
    private Users user;

    @Column(name = "CREATING_TIME")
    @Getter
    @Setter
    private Date creatingTime;

    @Column(name = "DELETING_TIME")
    @Getter
    @Setter
    private Date deletingTime;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "key")
    @Getter
    @Setter
    private RequestFromUser requestFromUser;
}
