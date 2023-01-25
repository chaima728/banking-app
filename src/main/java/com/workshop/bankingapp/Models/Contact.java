package com.workshop.bankingapp.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity

public class Contact extends AbstractEntity{
    private String firstname;
    private String lastname;
    private String email;
    private String iban ;

    @ManyToOne
    @JoinColumn(name = "user-id")
    private User user;

}
