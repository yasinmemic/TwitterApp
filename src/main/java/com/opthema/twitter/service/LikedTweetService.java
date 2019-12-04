package com.opthema.twitter.service;

import com.opthema.twitter.entity.LikedTweet;
import com.opthema.twitter.entity.Tweet;
import com.opthema.twitter.entity.Users;
import com.opthema.twitter.repository.LikedTweetRepository;
import com.opthema.twitter.repository.TweetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikedTweetService implements ILikedTweetService{

    private LikedTweetRepository likedTweetRepository;
    private TweetRepository tweetRepository;
    public LikedTweetService(LikedTweetRepository likedTweetRepository,TweetRepository tweetRepository) {
        this.likedTweetRepository = likedTweetRepository;
        this.tweetRepository = tweetRepository;
    }

    @Override
    public void likeTweet(Users user, Tweet tweet) {
        LikedTweet likedTweet = new LikedTweet();
        likedTweet.setUserId(user);
        likedTweet.setTweetId(tweet);
        likedTweetRepository.save(likedTweet);
    }

    @Override
    public List<LikedTweet> getAllLikedTweetByUserId(Users user) {
        return likedTweetRepository.findAllByUserId_Id(user.getId());
    }

    @Override
    public void cancelLikedTweet(LikedTweet likedTweet) {
        likedTweetRepository.delete(likedTweet);
    }

    @Override
    public void unLike(Tweet tweet, Long userId, Long tweetId) {
        LikedTweet likedTweet1 = likedTweetRepository.getAllByUserIdIdAndTweetIdId(userId,tweetId);
        tweetRepository.save(tweet);
        if(likedTweet1!=null){
            likedTweetRepository.delete(likedTweet1);
        }
    }


    @Override
    public LikedTweet getLikedTweet(Long userId, Long tweetId) {
        return likedTweetRepository.getAllByUserIdIdAndTweetIdId(userId,tweetId);
    }

    @Override
    public List<LikedTweet> findAllByTweetId_Id(Long tweetId) {
        return likedTweetRepository.findAllByTweetId_Id(tweetId);
    }


}
