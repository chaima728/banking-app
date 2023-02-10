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

public class Contact {
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
    private String iban ;

    @ManyToOne
    @JoinColumn(name = "user-id")
    private User user;

}
