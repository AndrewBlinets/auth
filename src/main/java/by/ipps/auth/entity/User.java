package by.ipps.auth.entity;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private String login;
  private String hashPassword;
  private String name;
  private String surName;
  private String patronicName;
  private List<String> roles;
  private String position;
  private String email;
  private long department;
  private boolean enabled;
  private boolean block;
  private Date dateLastChangePassword;
}
