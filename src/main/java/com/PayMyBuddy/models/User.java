package com.PayMyBuddy.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstname", nullable = false)
    @NotBlank(message = "please enter your firstname")
    private String firstName;

    @Column(name = "lastname", nullable = false)
    @NotBlank(message = "please enter your lastname")
    private String lastName;

    @Column(name = "mail", nullable = false, unique = true)
    @Email
    @NotBlank(message = "Please enter your email address")
    private String mail;

    @Column(name = "password", nullable = false)
    @Size(min = 8, message = "the password must contain at least 8 characters")
    @NotBlank(message = "Please enter your password")
    private String password;

    @Column(name = "wallet", nullable = false, columnDefinition = "int default 0")
    @Min(0)
    private int wallet;

    @OneToMany(mappedBy = "user_id")
    List<UserConnection> connections;

    @OneToMany(mappedBy = "user_id")
    List<Transaction>  transactions;
}
