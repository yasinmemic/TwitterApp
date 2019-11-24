package com.opthema.twitter.controller;

import com.opthema.twitter.entity.ReTweet;
import com.opthema.twitter.entity.Tweet;
import com.opthema.twitter.entity.User;
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
        User follower = userService.getUser(followerId);
        User followed = userService.getUser(followedId);
        Tweet tweet = tweetService.getTweet(tweetId);
       if(followingService.getFollowing(follower,followed) != null){
            reTweetService.reTweet(follower,tweet);
        }
        else
        System.out.println("İki kullanıcı takipleşmiyor...");

    }

 //   cancel retweet
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{followerId}/cancelRt/{followedId}/tweets/{tweetId}")
    public void cancelReTweet(@PathVariable("followerId") Long followerId, @PathVariable("followedId") Long followedId, @PathVariable("tweetId") Long tweetId){
        ReTweet reTweet = reTweetService.getReTweet(followerId,tweetId);
        reTweetService.cancelReTweet(reTweet);
    }
}
