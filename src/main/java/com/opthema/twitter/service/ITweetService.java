package com.opthema.twitter.service;

import com.opthema.twitter.entity.Tweet;

import java.util.List;

public interface ITweetService {

    List<Tweet> getAllTweets(Long userId);
    Tweet getTweet(Long tweetId);
    void addTweet(Tweet tweet);
    void deleteTweet(Long tweetId);

}
