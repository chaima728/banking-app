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

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity

public class Address {

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

    private String street;
    private Integer houseNumber;
    private Integer zipCode;
    private String city;
    private String country;

    @OneToOne
    private User user;

}
