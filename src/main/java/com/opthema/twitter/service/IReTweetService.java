package com.opthema.twitter.service;

import com.opthema.twitter.entity.ReTweet;
import com.opthema.twitter.entity.Tweet;
import com.opthema.twitter.entity.Users;

import java.util.List;

public interface IReTweetService {

    void reTweet(Users user, Tweet tweet);

    List<ReTweet> getAllReTweetByUserId(Users user);

    void cancelReTweet(ReTweet reTweet);

    ReTweet getReTweet(Long userId, Long tweetId);

    void unReTweet(Tweet tweet, Long userId, Long tweetId);

    List<ReTweet> findByTweetId_Id(Long tweetId);

}
