package com.workshop.bankingapp.Dtos;


import com.workshop.bankingapp.Models.Account;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDTo {

    private Integer id;
    private String iban;

    private LocalDateTime CreatedDate;
    private LocalDateTime LastModifiedDate;

    private UserDto userDto;



}
