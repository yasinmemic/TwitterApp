package com.opthema.twitter.controller;

import com.opthema.twitter.entity.LikedTweet;
import com.opthema.twitter.entity.ReTweet;
import com.opthema.twitter.entity.Tweet;
import com.opthema.twitter.entity.Users;
import com.opthema.twitter.service.IFollowingService;
import com.opthema.twitter.service.IReTweetService;
import com.opthema.twitter.service.ITweetService;
import com.opthema.twitter.service.IUserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReTweetController {

    IReTweetService reTweetService;
    ITweetService tweetService;
    IUserService userService;
    IFollowingService followingService;

    public ReTweetController(IReTweetService reTweetService, ITweetService tweetService, IUserService userService, IFollowingService followingService) {
        this.reTweetService = reTweetService;
        this.tweetService = tweetService;
        this.userService = userService;
        this.followingService = followingService;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/users/{followerId}/rt/{followedId}/tweets/{tweetId}")
    public void reTweet(@PathVariable("followerId") Long followerId, @PathVariable("followedId") Long followedId, @PathVariable("tweetId") Long tweetId){
        Users follower = userService.getUser(followerId);
        Tweet tweet = tweetService.getTweet(tweetId);
        ReTweet reTweet = reTweetService.getReTweet(followerId,tweetId);
        if(reTweet == null){
            tweet.setRtCount(tweet.getRtCount()+1);
            reTweetService.reTweet(follower,tweet);
        }
        else{
            tweet.setRtCount(tweet.getRtCount()-1);
            reTweetService.unReTweet(tweet,followerId,tweetId);
        }
    }


}
