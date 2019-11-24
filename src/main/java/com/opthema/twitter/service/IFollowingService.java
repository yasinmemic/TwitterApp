package com.opthema.twitter.service;

import com.opthema.twitter.entity.Following;
import com.opthema.twitter.entity.User;

import java.util.List;

public interface IFollowingService {

     void requestForFollowUser(User follower, User followed);
     void unFollowUser(User follower, User followed);
     Following getFollowing(User follower, User followedId);
     void acceptFollowRequest(Following following);
     List<Following> getAllFollowingByUserId (Long id);
}
