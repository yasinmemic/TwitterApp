package com.opthema.twitter.controller;

import com.opthema.twitter.entity.Tweet;
import com.opthema.twitter.entity.Users;
import com.opthema.twitter.model.UserRequest;
import com.opthema.twitter.model.UserResponse;
import com.opthema.twitter.service.IFollowingService;
import com.opthema.twitter.service.ITweetService;
import com.opthema.twitter.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080")

public class UserController extends BaseController {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private IUserService userService;
    private ITweetService tweetService;
    private IFollowingService followingService;

    public UserController(ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder, IUserService userService, ITweetService tweetService, IFollowingService followingService) {
        super(modelMapper);
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
        this.tweetService = tweetService;
        this.followingService = followingService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public void addUser(@RequestBody UserRequest userRequest) {
        System.out.println(userRequest.getUserName());
        userRequest.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        Users user = map(userRequest, Users.class);
        userService.saveUser(user);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        List<Tweet> tweetsOfUser = tweetService.getAllTweets(userId);
        int index = tweetsOfUser.size();

        for(int i=0; i<index; i++){
            tweetService.deleteTweet(tweetsOfUser.get(i).getId());
        }
        followingService.deleteByFollowerOrFollowed(userId,userId);
        userService.deleteUser(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value="/users/{userId}/profile/info")
    public UserResponse getUser(@PathVariable("userId") Long userId){
        Users user = userService.getUser(userId);
        UserResponse userResponse = map(user,UserResponse.class);
        return userResponse;
    }

}

