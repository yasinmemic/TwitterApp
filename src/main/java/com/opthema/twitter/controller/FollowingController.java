package com.opthema.twitter.controller;

import com.opthema.twitter.entity.Following;
import com.opthema.twitter.entity.Users;
import com.opthema.twitter.service.IFollowingService;
import com.opthema.twitter.service.IUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins ="http://localhost:8080")
public class FollowingController {

    private IFollowingService followingService;
    private IUserService userService;

    public FollowingController(IFollowingService followingService, IUserService userService) {
        this.followingService = followingService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/{followerUserId}/requestFollow/{followedUserName}")
    public void requestForFollowUser(@PathVariable("followerUserId") Long followerUserId, @PathVariable("followedUserName") String followedUserName) {
        Users follower = userService.getUser(followerUserId);
        Users followed = userService.getUserByUserName(followedUserName);
        if(follower.getUserName() != followed.getUserName()){
            followingService.requestForFollowUser(follower, followed);
        }
       else{
            System.out.println("kişi kendini takip edemez");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/{followerUserId}/unfollow/{followedUserId}")
    public void unFollowUser(@PathVariable("followerUserId") Long followerUserId, @PathVariable("followedUserId") Long followedUserId) {
        Users follower = userService.getUser(followerUserId);
        Users followed = userService.getUser(followedUserId);
        followingService.unFollowUser(follower, followed);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/{followedUserId}/acceptedRequest/{followerUserId}")
    public void acceptRequestFollow(@PathVariable("followerUserId") Long followerUserId, @PathVariable("followedUserId") Long followedUserId) {
        Users follower = userService.getUser(followerUserId);
        Users followed = userService.getUser(followedUserId);
        Following following = followingService.getFollowing(follower, followed);
        following.setAccepted(true);
        followingService.acceptFollowRequest(following);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/{followedUserId}/rejectedRequest/{followerUserId}")
    public void rejectRequestFollow(@PathVariable("followerUserId") Long followerUserId, @PathVariable("followedUserId") Long followedUserId) {
        Users follower = userService.getUser(followerUserId);
        Users followed = userService.getUser(followedUserId);
        followingService.unFollowUser(follower, followed);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{userId}/getRequests")
    public List<Following> getAllRequests(@PathVariable("userId") Long userId){
        List<Following> getRequests = followingService.getAllFollowingByFollowedId(userId);
        System.out.println(getRequests.size());
        for (int i=0; i<getRequests.size(); i++){
            if (getRequests.get(i).isAccepted() == true){
                getRequests.remove(i);
            }
            else{
               continue;
            }
        }
        System.out.println(getRequests.size());
        return getRequests;
    }


    @RequestMapping(method = RequestMethod.GET, value="/users/{userId}/followerCount")
    public int countOfFollower(@PathVariable("userId") Long userId){
        System.out.println(followingService.countOfFollower(userId));
        return followingService.countOfFollower(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value="/users/{userId}/followedCount")
    public int countOfFollowed(@PathVariable("userId") Long userId){
        if(followingService.countOfFollowed(userId) == 0){
            return 0;
        }
        return followingService.countOfFollowed(userId);
    }


    @RequestMapping(method = RequestMethod.GET, value="/users/{userId}/allFollowings")
    public List<Following> getAllFollowings(@PathVariable("userId") Long userId){

        return followingService.getAllFollowingByFollowerId(userId);
    }


    @RequestMapping(method = RequestMethod.GET, value="/users/{userId}/getAllFollowers")
    public List<Following> getAllFollowers(@PathVariable("userId") Long userId){

        return followingService.getAllFollowingByFollowedId2(userId);
    }

}
