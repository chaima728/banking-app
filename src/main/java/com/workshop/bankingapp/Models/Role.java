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

public class Role  {
    @Id
    @GeneratedValue
    private Integer id;

    @CreatedDate
    @Column(
            name="CreatedDate",
            nullable = false,
            updatable = false
    )
    private LocalDateTime CreatedDate;
    @LastModifiedDate
    @Column(
            name="LastModifiedDate"
    )
    private LocalDateTime LastModifiedDate;

    private String name;
}
