package com.example.prototipe.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.validator.constraints.Email;
import com.example.prototipe.entities.enums.Role;

import javax.validation.constraints.NotNull;


@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UsersDto {
    @Getter
    @Setter
    @NotNull
    private Long id;

    @Getter
    @Setter
    @Email
    private String email;

    @Getter
    @Setter
    private String fname;

    @Getter
    @Setter
    private String sname;

    @Getter
    @Setter
    private Short roleCode;

    @Getter
    @Setter
    private String group_num;

    @Getter
    @Setter
    private int faculNum;

    @Getter
    @Setter
    private int courseNum;

    @Getter
    @Setter
    private int summaryHelp;

    @Getter
    @Setter
    private int lastMonthHelp;



    public UsersDto(Long id, Role role, String fname, String sname,
                    String group_num, int faculNum, int courseNum,
                    int summaryHelp, int lastMonthHelp, String email){
        this.id = id;
        this.roleCode = role.getValue();
        this.fname = fname;
        this.sname = sname;
        this.group_num = group_num;
        this.faculNum = faculNum;
        this.courseNum = courseNum;
        this.summaryHelp = summaryHelp;
        this.lastMonthHelp = lastMonthHelp;
        this.email = email;
    }

    @JsonIgnore
    public Role getRole(){
        return Role.parse(this.roleCode);
    }

    @JsonIgnore
    public void setRole(Role role){
        this.roleCode = role.getValue();
    }
}
