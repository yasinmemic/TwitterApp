package com.opthema.twitter.service;

import com.opthema.twitter.entity.Following;
import com.opthema.twitter.entity.Users;

import java.util.List;

public interface IFollowingService {

     void requestForFollowUser(Users follower, Users followed);
     void unFollowUser(Users follower, Users followed);
     Following getFollowing(Users follower, Users followedId);
     void acceptFollowRequest(Following following);
     List<Following> getAllFollowingByFollowerId (Long id);
     List<Following> getAllFollowingByFollowedId(Long id);

     List<Following> getAllFollowingByFollowedId2(Long id);

     void deleteByFollowerOrFollowed(Long followerId, Long followedId);
     int countOfFollower(Long id);
     int countOfFollowed(Long id);
}
