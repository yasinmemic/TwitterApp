package com.opthema.twitter.repository;

import com.opthema.twitter.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Users getUserById (Long userId);
    Users getByUserName (String username);

    Users findByUserName(String userName);

}