package com.PayMyBuddy.models;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@Entity
@Table(name = "bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    @Column(name = "number", nullable = false)
    @NotNull(message = "please enter your bank account number")
    private int number;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "please enter a name for your bank account")
    private String name;

    @Override
    @Generated
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return id == that.id && number == that.number && Objects.equals(user_id, that.user_id) && Objects.equals(name, that.name);
    }

    @Override
    @Generated
    public int hashCode() {
        return Objects.hash(id, user_id, number, name);
    }

    @Override
    @Generated
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}
