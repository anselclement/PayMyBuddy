package com.PayMyBuddy.repository;

import com.PayMyBuddy.models.UserConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserConnectionRepository extends JpaRepository<UserConnection, Integer> {

    @Query(value = "Select id, firstname, lastname, user_id, mail from user_connection where user_id = :id", nativeQuery = true)
    List<UserConnection> getAllUserContactById(int id);
}
