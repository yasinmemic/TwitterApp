package com.opthema.twitter.repository;
import com.opthema.twitter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserById (Long userId);
    User getByUserName (String username);
}