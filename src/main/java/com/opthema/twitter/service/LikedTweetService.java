package com.opthema.twitter.service;

import com.opthema.twitter.entity.LikedTweet;
import com.opthema.twitter.entity.Tweet;
import com.opthema.twitter.entity.User;
import com.opthema.twitter.repository.LikedTweetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikedTweetService implements ILikedTweetService{

    private LikedTweetRepository likedTweetRepository;

    public LikedTweetService(LikedTweetRepository likedTweetRepository) {
        this.likedTweetRepository = likedTweetRepository;
    }

    @Override
    public void likeTweet(User user, Tweet tweet) {
        LikedTweet likedTweet = new LikedTweet();
        likedTweet.setUserId(user);
        likedTweet.setTweetId(tweet);
        likedTweetRepository.save(likedTweet);
    }

    @Override
    public List<LikedTweet> getAllLikedTweetByUserId(User user) {
        return likedTweetRepository.findAllByUserId_Id(user.getId());
    }

    @Override
    public void cancelLikedTweet(LikedTweet likedTweet) {
        likedTweetRepository.delete(likedTweet);
    }

    @Override
    public LikedTweet getLikedTweet(Long userId, Long tweetId) {
        return likedTweetRepository.getAllByUserId_IdAndTweetId_Id(userId,tweetId);
    }
}
