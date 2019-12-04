package com.opthema.twitter.repository;

import com.opthema.twitter.entity.LikedTweet;
import com.opthema.twitter.entity.ReTweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReTweetRepository extends JpaRepository<ReTweet,Long> {

    List<ReTweet> findAllByUserId_Id(Long userId);
    ReTweet getAllByUserId_IdAndTweetId_Id(Long userId, Long tweetId);
    void deleteAllByTweetId_Id(Long tweetId);
    ReTweet findAllByUserId_IdAndTweetId_Id(Long userId, Long tweetId);
    List<ReTweet> findAllByTweetId_Id(Long tweetId);
}
