package com.tolgaybalci.thy.repository;

import com.tolgaybalci.thy.entity.ThyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ThyUserRepository extends JpaRepository<ThyUser, String> {

    ThyUser findByUsername(String username);
    ThyUser findByUsernameOrEmail(String username, String email);

    @Query("select thyUser FROM ThyUser thyUser WHERE (thyUser.username = :username or thyUser.email = :email) and thyUser.password = :password")
    ThyUser login(@Param("username") String username, @Param("email") String email, @Param("password") String password);

}
