package com.opthema.twitter.service;

import com.opthema.twitter.entity.Following;
import com.opthema.twitter.entity.User;
import com.opthema.twitter.repository.FollowingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowingService implements IFollowingService {
    private FollowingRepository followingRepository;
    public FollowingService(FollowingRepository followingRepository) {
        this.followingRepository = followingRepository;
    }
    @Override
    public void requestForFollowUser(User follower, User followed) {
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
    public Following getFollowing(User follower, User followed) {
        return followingRepository.findByFollower_IdAndFollowed_Id(follower.getId(),followed.getId());
    }

    @Override
    public void acceptFollowRequest(Following following) {
        followingRepository.save(following);
    }

    @Override
    public List<Following> getAllFollowingByUserId(Long id) {
        return followingRepository.findAllByFollower_Id(id);
    }
}
