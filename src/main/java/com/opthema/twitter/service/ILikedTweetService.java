package com.opthema.twitter.service;

import com.opthema.twitter.entity.LikedTweet;
import com.opthema.twitter.entity.Tweet;
import com.opthema.twitter.entity.User;

import java.util.List;

public interface ILikedTweetService {

    void likeTweet(User user, Tweet tweet);
    List<LikedTweet> getAllLikedTweetByUserId(User user);
    void cancelLikedTweet(LikedTweet likedTweet);
    LikedTweet getLikedTweet(Long userId, Long tweetId);
}
