package com.banking.banking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String secondName;
    private String email;
    private String password;
    @Column(length = 10,name = "Phone Number")
    private String phoneNumber;
    private String role;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user",cascade = CascadeType.ALL)
            @Column(name="Accounts")
    private List<Account>accountList;

}
