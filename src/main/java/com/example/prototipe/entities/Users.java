package com.example.prototipe.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Table(name = "USERS")
@ToString
public class Users {
    @Id
    @Column(name = "id")
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "user_id_sequence")
    private Long id;

    @Column(name = "EMAIL")
    @Getter
    @Setter
    @NotNull
    private String email;

    @Column(name = "FNAME")
    @Getter
    @Setter
    @NotNull
    private String fname;

    @Column(name = "SNAME")
    @Getter
    @Setter
    @NotNull
    private String sname;

    @Column(name = "GROUP_NUM")
    @Getter
    @Setter
    @NotNull
    private String group_num;

    @Column(name = "PASSWORD_HASH")
    @Getter
    @Setter
    @NotNull
    private String password_hash;

    @Column(name = "PASSWORD_SALT")
    @Getter
    @Setter
    @NotNull
    private String password_salt;

    //Else properties of user


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @Getter
    @Setter
    private List<HeadmenRequest> requests;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    @Getter
    @Setter
    private TimeOfRequesting timeOfRequesting;


}
