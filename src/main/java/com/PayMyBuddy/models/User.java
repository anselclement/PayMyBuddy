package com.PayMyBuddy.models;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Objects;

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

    @Column(name = "wallet", nullable = false, columnDefinition = "Decimal(10,2) default '0.00'")
    @Min(0)
    private double wallet;

    @OneToMany(mappedBy = "user_id")
    List<UserConnection> connections;

    @OneToMany(mappedBy = "user_id")
    List<Transaction>  transactions;

    @OneToMany(mappedBy = "user_id")
    List<BankAccount>  bankAccounts;

    @Override
    @Generated
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Double.compare(user.wallet, wallet) == 0 && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(mail, user.mail) && Objects.equals(password, user.password) && Objects.equals(connections, user.connections) && Objects.equals(transactions, user.transactions) && Objects.equals(bankAccounts, user.bankAccounts);
    }

    @Override
    @Generated
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, mail, password, wallet, connections, transactions, bankAccounts);
    }

    @Override
    @Generated
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", wallet=" + wallet +
                ", connections=" + connections +
                ", transactions=" + transactions +
                ", bankAccounts=" + bankAccounts +
                '}';
    }
}
