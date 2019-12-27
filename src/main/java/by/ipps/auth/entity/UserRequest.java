package by.ipps.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String name;
    private String surName;
    private String patronicName;
    private List<String> roles;
    private String token;
}
