package com.opthema.twitter.service;

import com.opthema.twitter.entity.Tweet;
import com.opthema.twitter.repository.TweetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetService implements ITweetService {

    private TweetRepository tweetRepository;

    public TweetService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    public List<Tweet> getAllTweets(Long userId) {
       return tweetRepository.getTweetsByUserId(userId);
    }

    @Override
    public Tweet getTweet(Long tweetId) {
        return tweetRepository.getOne(tweetId);
    }

    @Override
    public void addTweet(Tweet tweet) {
        tweetRepository.save(tweet);
    }

    @Override
    public void deleteTweet(Long tweetId) {
        tweetRepository.deleteById(tweetId);
    }
}
