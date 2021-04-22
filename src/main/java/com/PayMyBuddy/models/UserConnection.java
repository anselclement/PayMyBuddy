package com.PayMyBuddy.models;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

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

    @Override
    @Generated
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserConnection that = (UserConnection) o;
        return id == that.id && Objects.equals(user_id, that.user_id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(mail, that.mail);
    }

    @Override
    @Generated
    public int hashCode() {
        return Objects.hash(id, user_id, firstName, lastName, mail);
    }

    @Override
    @Generated
    public String toString() {
        return "UserConnection{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
