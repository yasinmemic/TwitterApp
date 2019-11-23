package com.opthema.twitter.controller;

import com.opthema.twitter.entity.Tweet;
import com.opthema.twitter.entity.User;
import com.opthema.twitter.model.TweetRequest;
import com.opthema.twitter.model.TweetResponse;
import com.opthema.twitter.model.UserRequest;
import com.opthema.twitter.service.ITweetService;
import com.opthema.twitter.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TweetController extends BaseController {

    private ITweetService tweetService;
    private IUserService userService;
    public TweetController(ModelMapper modelMapper, ITweetService tweetService, IUserService userService) {
        super(modelMapper);
        this.tweetService = tweetService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/tweets", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<TweetResponse> getAllTweets(@PathVariable("userId") Long userId){
       List<Tweet> tweets = tweetService.getAllTweets(userId);
       List<TweetResponse> tweetResponses = mapAll(tweets,TweetResponse.class);
       return tweetResponses;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/tweets/{tweetId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TweetResponse getTweet(@PathVariable("userId") Long userId, @PathVariable("tweetId") Long tweetId){
        Tweet tweet = tweetService.getTweet(tweetId);
        TweetResponse tweetResponse = map(tweet,TweetResponse.class);
        return tweetResponse;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/users/{userId}/tweets", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addTweet(@RequestBody TweetRequest tweetRequest, @PathVariable("userId") Long userId) {
        User user = userService.getUser(userId);
        Tweet tweet = map(tweetRequest, Tweet.class);
        tweet.setUser(user);
        tweetService.addTweet(tweet);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{userId}/tweets/{tweetId}", consumes = MediaType.APPLICATION_JSON_VALUE )
    public void deleteTweet(@PathVariable("tweetId") Long tweetId){
        tweetService.deleteTweet(tweetId);
    }
}
