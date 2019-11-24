package com.opthema.twitter.repository;

import com.opthema.twitter.entity.LikedTweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikedTweetRepository extends JpaRepository<LikedTweet,Long> {
    List<LikedTweet> findAllByUserId_Id(Long userId);
   LikedTweet getAllByUserId_IdAndTweetId_Id(Long userId, Long tweetId);
}
