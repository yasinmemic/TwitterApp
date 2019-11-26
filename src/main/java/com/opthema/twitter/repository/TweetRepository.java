package com.opthema.twitter.repository;

import com.opthema.twitter.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet,Long> {
    List<Tweet> getTweetsByUserIdOrderByCreatedAtDesc (Long userId);

}
