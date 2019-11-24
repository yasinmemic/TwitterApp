package com.opthema.twitter.repository;

import com.opthema.twitter.entity.Following;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowingRepository extends JpaRepository<Following,Long> {

    Following findByFollower_IdAndFollowed_Id(Long followerId, Long followedId);
    List<Following> findAllByFollower_Id(Long followerId);
}
