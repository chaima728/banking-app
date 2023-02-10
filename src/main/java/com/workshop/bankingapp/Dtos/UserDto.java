package com.workshop.bankingapp.Dtos;


import com.sun.istack.NotNull;
import com.workshop.bankingapp.Models.User;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Integer id;

    @NotNull
    @NotEmpty(message = "first name can not be empty")
    @NotBlank(message = "first name can not be blank")
    private String firstname;
    @NotNull
    @NotEmpty(message = "last name can not be empty")
    @NotBlank(message = "last name can not be blank")
    private String lastname;
    @NotNull
    @NotEmpty(message = "email can not be empty")
    @NotBlank(message = "email can not be blank")
    @Email
    private String email;
    @NotNull
    @NotEmpty(message = "password can not be empty")
    @NotBlank(message = "password can not be blank")
    @Size(min = 8, max = 16 , message = "password must be between 8 and 16 caracters")
    private String password;

    //Mapping between Dto and Entity



}
