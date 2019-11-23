package com.opthema.twitter.controller;

import com.opthema.twitter.entity.Following;
import com.opthema.twitter.entity.User;
import com.opthema.twitter.repository.FollowingRepository;
import com.opthema.twitter.service.IFollowingService;
import com.opthema.twitter.service.IUserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowingController {

    private IFollowingService followingService;
    private IUserService userService;

    public FollowingController(IFollowingService followingService, IUserService userService) {
        this.followingService = followingService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/{followerUserId}/follow/{followedUserId}")
    public void followUser(@PathVariable("followerUserId") Long followerUserId, @PathVariable("followedUserId") Long followedUserId) {
        User follower = userService.getUser(followerUserId);
        User followed = userService.getUser(followedUserId);
        followingService.followUser(follower,followed);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{followerUserId}/unfollow/{followedUserId}")
    public void unFollowUser(@PathVariable("followerUserId") Long followerUserId, @PathVariable("followedUserId") Long followedUserId) {
        User follower = userService.getUser(followerUserId);
        User followed = userService.getUser(followedUserId);
        followingService.unFollowUser(follower,followed);
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/users/{followerUserId}/accepted/{followedUserId}")
    public void accepted(@PathVariable("followerUserId") Long followerUserId, @PathVariable("followedUserId") Long followedUserId) {
        User follower = userService.getUser(followerUserId);
        User followed = userService.getUser(followedUserId);
        Following following = followingService.accepted(follower,followed);
        following.setAccepted(true);
        followingService.updateState(following);
    }
}
