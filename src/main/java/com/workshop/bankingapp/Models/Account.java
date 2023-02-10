package com.workshop.bankingapp.Models;

import lombok.*;
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

public class Account {
    @Id
    @GeneratedValue
    private Integer id;
    private String iban ;

    @CreatedDate
    @Column(
            name="CreatedDate"
    )
    private LocalDateTime CreatedDate;

    @LastModifiedDate
    @Column(
            name="LastModifiedDate"
    )
    private LocalDateTime LastModifiedDate;
    @OneToOne
    @JoinColumn(name = "user-id")
    private User user;

}
