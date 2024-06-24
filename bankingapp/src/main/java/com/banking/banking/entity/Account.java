package com.banking.banking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int account_id;
    private String bankName;
    private String accountNumber;
    private double amount;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id")
    private User user;
}
