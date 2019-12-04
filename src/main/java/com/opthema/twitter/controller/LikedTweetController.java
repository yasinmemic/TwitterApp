package com.opthema.twitter.controller;


import com.opthema.twitter.entity.LikedTweet;
import com.opthema.twitter.entity.Tweet;
import com.opthema.twitter.entity.Users;
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
        Users follower = userService.getUser(followerId);
        Tweet tweet = tweetService.getTweet(tweetId);
        LikedTweet likedTweet = likedTweetService.getLikedTweet(followerId,tweetId);
        if(likedTweet == null){
            tweet.setLikedCount(tweet.getLikedCount()+1);
            likedTweetService.likeTweet(follower,tweet);
        }
        else{
            tweet.setLikedCount(tweet.getLikedCount()-1);
            likedTweetService.unLike(tweet,followerId,tweetId);
        }
    }

}
