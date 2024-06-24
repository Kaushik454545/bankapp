package com.banking.banking.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private String firstName;
    private String secondName;
    private String email;
    private String phoneNumber;
    private String role;
}
