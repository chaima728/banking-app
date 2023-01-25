package com.workshop.bankingapp.Dtos;


import com.workshop.bankingapp.Models.Account;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTo {

    private String iban;
    private UserDto userDto;

    // mapping between dto and entity


}
