package com.opthema.twitter.controller;


import com.opthema.twitter.entity.LikedTweet;
import com.opthema.twitter.entity.Tweet;
import com.opthema.twitter.entity.User;
import com.opthema.twitter.service.IFollowingService;
import com.opthema.twitter.service.ILikedTweetService;
import com.opthema.twitter.service.ITweetService;
import com.opthema.twitter.service.IUserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikedTweetController {

    ILikedTweetService likedTweetService;
    ITweetService tweetService;
    IUserService userService;
    IFollowingService followingService;

    public LikedTweetController(ILikedTweetService likedTweetService, ITweetService tweetService, IUserService userService, IFollowingService followingService) {
        this.likedTweetService = likedTweetService;
        this.tweetService = tweetService;
        this.userService = userService;
        this.followingService = followingService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/{followerId}/liked/{followedId}/tweets/{tweetId}")
    public void likeTweet(@PathVariable("followerId") Long followerId, @PathVariable("followedId") Long followedId, @PathVariable("tweetId") Long tweetId){
        User follower = userService.getUser(followerId);
        User followed = userService.getUser(followedId);
        Tweet tweet = tweetService.getTweet(tweetId);
        if(followingService.getFollowing(follower,followed) != null){
            likedTweetService.likeTweet(follower,tweet);
        }
        else
            System.out.println("İki kullanıcı takipleşmiyor...");

    }

    //   cancel liked
    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{followerId}/cancelLiked/{followedId}/tweets/{tweetId}")
    public void cancelLikedTweet(@PathVariable("followerId") Long followerId, @PathVariable("followedId") Long followedId, @PathVariable("tweetId") Long tweetId){
        LikedTweet likedTweet = likedTweetService.getLikedTweet(followerId,tweetId);
        likedTweetService.cancelLikedTweet(likedTweet);
    }
}
