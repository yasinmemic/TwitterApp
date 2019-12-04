package com.opthema.twitter.service;

import com.opthema.twitter.entity.LikedTweet;
import com.opthema.twitter.entity.ReTweet;
import com.opthema.twitter.entity.Tweet;
import com.opthema.twitter.repository.FollowingRepository;
import com.opthema.twitter.repository.LikedTweetRepository;
import com.opthema.twitter.repository.ReTweetRepository;
import com.opthema.twitter.repository.TweetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetService implements ITweetService {

    private TweetRepository tweetRepository;
    private ILikedTweetService likedTweetService;
    private IReTweetService reTweetService;

    public TweetService(TweetRepository tweetRepository, ILikedTweetService likedTweetService, IReTweetService reTweetService) {
        this.tweetRepository = tweetRepository;
        this.likedTweetService = likedTweetService;
        this.reTweetService = reTweetService;
    }

    @Override
    public List<Tweet> getAllTweets(Long userId) {
        return tweetRepository.getTweetsByUserIdOrderByCreatedAtDesc(userId);
    }

    @Override
    public Tweet getTweet(Long tweetId) {
        return tweetRepository.getTweetById(tweetId);
    }

    @Override
    public void addTweet(Tweet tweet) {
        tweetRepository.save(tweet);
    }

    @Override
    public void deleteTweet(Long tweetId) {
        List<LikedTweet> likedTweet = likedTweetService.findAllByTweetId_Id(tweetId);
        List<ReTweet> reTweet = reTweetService.findByTweetId_Id(tweetId);
        if (likedTweet != null)
            for (int i = 0; i < likedTweet.size(); i++) {
                likedTweetService.cancelLikedTweet(likedTweet.get(i));
            }

        if (reTweet != null)
            for (int i = 0; i < reTweet.size(); i++) {
                reTweetService.cancelReTweet(reTweet.get(i));
            }
        tweetRepository.deleteById(tweetId);
    }


}
