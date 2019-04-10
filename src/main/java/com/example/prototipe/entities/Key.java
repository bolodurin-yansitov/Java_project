package com.example.prototipe.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "KEY")
@ToString(exclude = "user")
public class Key {
    @Id
    @Column(name = "CLOSE_KEY")
    @Getter
    @Setter
    @NotNull
    private Long closeKey;

    @Column(name = "OPEN_KEY")
    @Getter
    @Setter
    @NotNull
    private Long openKey;

    @Column(name = "USER_ID")
    @Getter
    @Setter
    @NotNull
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
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
