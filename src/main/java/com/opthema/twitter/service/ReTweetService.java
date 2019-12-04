package com.opthema.twitter.service;

import com.opthema.twitter.entity.LikedTweet;
import com.opthema.twitter.entity.ReTweet;
import com.opthema.twitter.entity.Tweet;
import com.opthema.twitter.entity.Users;
import com.opthema.twitter.repository.ReTweetRepository;
import com.opthema.twitter.repository.TweetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReTweetService implements IReTweetService {
    private ReTweetRepository reTweetRepository;
    private TweetRepository tweetRepository;
    public ReTweetService(ReTweetRepository reTweetRepository,TweetRepository tweetRepository) {
        this.reTweetRepository = reTweetRepository;
        this.tweetRepository = tweetRepository;
    }

    @Override
    public void reTweet(Users user, Tweet tweet) {
        ReTweet reTweet = new ReTweet();
        reTweet.setUserId(user);
        reTweet.setTweetId(tweet);
        reTweetRepository.save(reTweet);
    }

    @Override
    public List<ReTweet> getAllReTweetByUserId(Users user) {
        return reTweetRepository.findAllByUserId_Id(user.getId());
    }


    @Override
    public void cancelReTweet(ReTweet reTweet) {
         reTweetRepository.delete(reTweet);
    }
    @Override
    public void unReTweet(Tweet tweet, Long userId, Long tweetId) {
        ReTweet reTweet = reTweetRepository.getAllByUserId_IdAndTweetId_Id(userId,tweetId);
        tweetRepository.save(tweet);
        if(reTweet!=null){
            reTweetRepository.delete(reTweet);
        }
    }

    @Override
    public List<ReTweet> findByTweetId_Id(Long tweetId) {
        return reTweetRepository.findAllByTweetId_Id(tweetId);
    }


    @Override
    public ReTweet getReTweet(Long userId, Long tweetId) {
        return reTweetRepository.getAllByUserId_IdAndTweetId_Id(userId,tweetId);
    }




}
