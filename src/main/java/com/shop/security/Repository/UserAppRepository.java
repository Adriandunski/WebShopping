package com.shop.security.Repository;

import com.shop.security.Model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface UserAppRepository extends JpaRepository<UserApp, Integer> {

    @Query(value = "select u from UserApp u where u.username = ?1")
    Optional<UserApp> findUserAppByName(String username);
}
