package com.opthema.twitter.service;

import com.opthema.twitter.entity.ReTweet;
import com.opthema.twitter.entity.Tweet;
import com.opthema.twitter.entity.User;
import com.opthema.twitter.repository.ReTweetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReTweetService implements IReTweetService {
    private ReTweetRepository reTweetRepository;

    public ReTweetService(ReTweetRepository reTweetRepository) {
        this.reTweetRepository = reTweetRepository;
    }

    @Override
    public void reTweet(User user, Tweet tweet) {
        ReTweet reTweet = new ReTweet();
        reTweet.setUserId(user);
        reTweet.setTweetId(tweet);
        reTweetRepository.save(reTweet);
    }

    @Override
    public List<ReTweet> getAllReTweetByUserId(User user) {
        return reTweetRepository.findAllByUserId_Id(user.getId());
    }


    @Override
    public void cancelReTweet(ReTweet reTweet) {
         reTweetRepository.delete(reTweet);
    }

    @Override
    public ReTweet getReTweet(Long userId, Long tweetId) {
        return reTweetRepository.getAllByUserId_IdAndTweetId_Id(userId,tweetId);
    }


}
