package com.opthema.twitter.controller;

import com.opthema.twitter.entity.*;
import com.opthema.twitter.model.TweetRequest;
import com.opthema.twitter.model.TweetResponse;
import com.opthema.twitter.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class TweetController extends BaseController {

    private ITweetService tweetService;
    private IUserService userService;
    private IFollowingService followingService;
    private IReTweetService reTweetService;
    private ILikedTweetService likedTweetService;

    public TweetController(ModelMapper modelMapper, ITweetService tweetService, IUserService userService, IFollowingService followingService, IReTweetService reTweetService, ILikedTweetService likedTweetService) {
        super(modelMapper);
        this.tweetService = tweetService;
        this.userService = userService;
        this.followingService = followingService;
        this.reTweetService = reTweetService;
        this.likedTweetService = likedTweetService;
    }

    //retweet processing
    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/profile")
    public List<TweetResponse> getAllTweetsForProfileTimeline(@PathVariable("userId") Long userId) {
        List<Tweet> tweets = tweetService.getAllTweets(userId);
        List<TweetResponse> tweetResponses = mapAll(tweets, TweetResponse.class);
        Users user = userService.getUser(userId);
        List<ReTweet> reTweetList = reTweetService.getAllReTweetByUserId(user);
        List<Tweet> tweetList = new ArrayList<>();
        reTweetList.forEach(x -> tweetList.add(tweetService.getTweet(x.getTweetId().getId())));
        List<TweetResponse> allTweetResponses = mapAll(tweetList, TweetResponse.class);
        allTweetResponses.addAll(tweetResponses);
        return allTweetResponses;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/liked")
    public List<TweetResponse> getAllLikedTweets(@PathVariable("userId") Long userId) {
        Users user = userService.getUser(userId);
        List<LikedTweet> likedTweets = likedTweetService.getAllLikedTweetByUserId(user);
        List<Tweet> tweetList = new ArrayList<>();
        likedTweets.forEach(x -> tweetList.add(tweetService.getTweet(x.getTweetId().getId())));
        List<TweetResponse> allTweetResponses = mapAll(tweetList, TweetResponse.class);
        return allTweetResponses;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/home")
    public List<TweetResponse> getAllTweetsHomeTimeline(@PathVariable("userId") Long userId) {
        List<Tweet> tweets = tweetService.getAllTweets(userId);
        List<TweetResponse> tweetResponses = mapAll(tweets, TweetResponse.class);
        //------------------------------------------------- get following user tweeets
        List<Following> otherUsers = followingService.getAllFollowingByFollowerId(userId);
        List<Tweet> otherUsersTweets = new ArrayList<>();

        for (int i = 0; i < otherUsers.size(); i++) {
            if (otherUsers.get(i).isAccepted() == true) {
                continue;
            } else {
                otherUsers.remove(i);
                i--;
            }
        }
        otherUsers.forEach(x -> tweetService.getAllTweets(x.getFollowed().getId()).forEach(y -> otherUsersTweets.add(y)));
        //------------------------------------------------ get following user tweeets
        List<TweetResponse> allTweetResponses = mapAll(otherUsersTweets, TweetResponse.class);
        allTweetResponses.addAll(tweetResponses);
        Collections.sort(allTweetResponses);
        return allTweetResponses;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/tweets/{tweetId}")
    public TweetResponse getTweet(@PathVariable("userId") Long userId, @PathVariable("tweetId") Long tweetId) {
        Tweet tweet = tweetService.getTweet(tweetId);
        TweetResponse tweetResponse = map(tweet, TweetResponse.class);
        return tweetResponse;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/users/{userId}/tweets")
    public void addTweet(@RequestBody TweetRequest tweetRequest, @PathVariable("userId") Long userId) {
        Users user = userService.getUser(userId);
        Tweet tweet = map(tweetRequest, Tweet.class);
        tweet.setUser(user);
        tweetService.addTweet(tweet);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/{userId}/deleteTweet/{tweetId}")
    public void deleteTweet(@PathVariable("userId") Long userId, @PathVariable("tweetId") Long tweetId) {
        Tweet tweet = tweetService.getTweet(tweetId);
        tweetService.deleteTweet(tweet.getId());
    }
}