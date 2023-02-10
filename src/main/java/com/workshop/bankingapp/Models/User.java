package com.workshop.bankingapp.Models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name ="_name")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @org.springframework.data.annotation.CreatedDate
    @Column(
            name="CreatedDate",
            nullable = false,
            updatable = false
    )
    private LocalDateTime CreatedDate;
    @org.springframework.data.annotation.LastModifiedDate
    @Column(
            name="LastModifiedDate"
    )
    private LocalDateTime LastModifiedDate;

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private boolean active;
    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "user")
    private List<Contact> contacts;

    @OneToOne
    private Account account;

    @OneToOne
    private Address address;
    @OneToOne
    private Role role;

}
