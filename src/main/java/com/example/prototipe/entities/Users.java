package com.example.prototipe.entities;

import com.example.prototipe.entities.enums.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "USERS")
@ToString
public class Users {
    @Id
    @Column(name = "ID")
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "user_seq_gen")
    @SequenceGenerator(name = "user_seq_gen", sequenceName =
            "user_id_sequence", allocationSize = 1)
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
    private String groupNum;

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
    @Column(name = "ROLE")
    private short role;

    public Role getRole(){
        return Role.parse(this.role);
    }

    public void setRole(Role role1){
        this.role = role1.getValue();
    }

    @Column(name = "COURSE_NUM")
    @Getter
    @Setter
    @NotNull
    private int courseNum;

    @Column(name = "FACUL_NUM")
    @Getter
    @Setter
    @NotNull
    private int faculNum;

    @Column(name = "START_BAN")
    @Getter
    @Setter
    @NotNull
    private Date startBan;

    @Column(name = "END_BAN")
    @Getter
    @Setter
    @NotNull
    private Date endBan;

    @Column(name = "SUMMARY_HELP")
    @Getter
    @Setter
    private int summaryHelp;

    @Column(name = "HELP_FOR_LAST_MONTH")
    @Getter
    @Setter
    private int lastMonthHelp;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @Getter
    @Setter
    private List<HeadmansRequest> requests;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    @Getter
    @Setter
    private TimeOfRequesting timeOfRequesting;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    @Getter
    @Setter
    private Key key;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "USERS_REQUESTS",
            joinColumns = {@JoinColumn(name = "request_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    @Getter
    @Setter
    private List<RequestFromUser> requestsFromUser;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "HEADMAN_REQUEST",
            joinColumns = {@JoinColumn(name = "request_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    @Getter
    @Setter
    private List<RequestFromUser> headmanRequest;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "CHAIRMAN_REQUEST",
            joinColumns = {@JoinColumn(name = "request_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    @Getter
    @Setter
    private List<HeadmansRequest> chairmanRequest;

}
