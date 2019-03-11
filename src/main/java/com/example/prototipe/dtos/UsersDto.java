package com.example.prototipe.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.validator.constraints.Email;
import com.example.prototipe.entities.enums.Role;


@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class UsersDto {
    @Getter
    @Setter
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
    private Short role;

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


}
