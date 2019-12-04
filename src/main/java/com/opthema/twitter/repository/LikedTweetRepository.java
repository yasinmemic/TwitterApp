package com.opthema.twitter.repository;

import com.opthema.twitter.entity.LikedTweet;
import com.opthema.twitter.entity.ReTweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikedTweetRepository extends JpaRepository<LikedTweet, Long> {
    List<LikedTweet> findAllByUserId_Id(Long userId);

    LikedTweet getAllByUserIdIdAndTweetIdId(Long userId, Long tweetId);

    List<LikedTweet> getAllByTweetId_Id(Long tweetId);

    List<LikedTweet> findAllByTweetId_Id(Long tweetId);

}
