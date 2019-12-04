package com.opthema.twitter.service;

import com.opthema.twitter.entity.LikedTweet;
import com.opthema.twitter.entity.Tweet;
import com.opthema.twitter.entity.Users;

import java.util.List;

public interface ILikedTweetService {

    void likeTweet(Users user, Tweet tweet);

    List<LikedTweet> getAllLikedTweetByUserId(Users user);

    void cancelLikedTweet(LikedTweet likedTweet);

    void unLike(Tweet tweet, Long userId, Long tweetId);

    LikedTweet getLikedTweet(Long userId, Long tweetId);
    List<LikedTweet> findAllByTweetId_Id(Long tweetId);
}
