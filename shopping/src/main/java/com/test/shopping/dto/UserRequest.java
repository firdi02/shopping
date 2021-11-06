package com.test.shopping.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String username;

    private String password;

    private String email;

    private String phone;

    private String contry;

    private String city;

    private String postcode;

    private String name;

    private String address;
}
