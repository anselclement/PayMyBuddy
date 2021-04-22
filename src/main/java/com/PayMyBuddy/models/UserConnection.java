package com.PayMyBuddy.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "user_connection")
public class UserConnection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

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
}
