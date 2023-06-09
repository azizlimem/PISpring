package com.example.marketplace.Playload.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    private String Username;

    private String firstName;

    private String lastName;

    private String email;

    private List<String> roles;

    private String password;

    private String cinUser;

    private   String phoneNumber;



}
