package com.workshop.bankingapp.Dtos;


import com.workshop.bankingapp.Models.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDto {

    private Integer id;

    private String firstname;
    private String lastname;
    private String email;
    private String password;

    //Mapping between Dto and Entity



}
