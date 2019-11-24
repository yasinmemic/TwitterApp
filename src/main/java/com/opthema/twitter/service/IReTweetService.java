package com.opthema.twitter.service;

import com.opthema.twitter.entity.ReTweet;
import com.opthema.twitter.entity.Tweet;
import com.opthema.twitter.entity.User;

import java.util.List;

public interface IReTweetService {

    void reTweet(User user, Tweet tweet);

    List<ReTweet> getAllReTweetByUserId(User user);
    void cancelReTweet(ReTweet reTweet);
    ReTweet getReTweet(Long userId, Long tweetId);
}
