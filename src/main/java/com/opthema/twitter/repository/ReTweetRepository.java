package com.opthema.twitter.repository;

import com.opthema.twitter.entity.ReTweet;
import com.opthema.twitter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReTweetRepository extends JpaRepository<ReTweet,Long> {

    List<ReTweet> findAllByUserId_Id(Long userId);
    ReTweet getAllByUserId_IdAndTweetId_Id(Long userId, Long tweetId);
}
