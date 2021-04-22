package com.PayMyBuddy.models;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    private String connection;

    private String description;

    @Column(name = "amount", columnDefinition = "Decimal(10,2)")
    private double amount;

    @Override
    @Generated
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id && Double.compare(that.amount, amount) == 0 && Objects.equals(user_id, that.user_id) && Objects.equals(connection, that.connection) && Objects.equals(description, that.description);
    }

    @Override
    @Generated
    public int hashCode() {
        return Objects.hash(id, user_id, connection, description, amount);
    }

    @Override
    @Generated
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", connection='" + connection + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}
