package com.opthema.twitter.service;

import com.opthema.twitter.entity.Following;
import com.opthema.twitter.entity.User;

public interface IFollowingService {

     void followUser(User follower, User followed);
     void unFollowUser(User follower, User followed);
     Following accepted(User follower, User followedId);
     void updateState(Following following);
}
