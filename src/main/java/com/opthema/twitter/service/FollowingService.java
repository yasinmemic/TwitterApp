package com.opthema.twitter.service;

import com.opthema.twitter.entity.Following;
import com.opthema.twitter.entity.User;
import com.opthema.twitter.repository.FollowingRepository;
import org.springframework.stereotype.Service;

@Service
public class FollowingService implements IFollowingService {
    private FollowingRepository followingRepository;
    public FollowingService(FollowingRepository followingRepository) {
        this.followingRepository = followingRepository;
    }
    @Override
    public void followUser(User follower, User followed) {
       Following following = new Following();
       following.setFollower(follower);
       following.setFollowed(followed);
       followingRepository.save(following);
    }
    @Override
    public void unFollowUser(User follower, User followed) {
        Following following = followingRepository.findByFollower_IdAndFollowed_Id(follower.getId(),followed.getId());
        followingRepository.delete(following);
    }

    @Override
    public Following accepted(User follower, User followed) {
        return followingRepository.findByFollower_IdAndFollowed_Id(follower.getId(),followed.getId());
    }

    @Override
    public void updateState(Following following) {
        followingRepository.save(following);
    }
}
