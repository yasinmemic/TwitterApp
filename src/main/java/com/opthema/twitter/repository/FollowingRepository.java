package com.opthema.twitter.repository;

import com.opthema.twitter.entity.Following;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowingRepository extends JpaRepository<Following,Long> {

    Following findByFollower_IdAndFollowed_Id(Long followerId, Long followedId);
    List<Following> findAllByFollower_Id(Long followerId);
    List<Following> getAllByFollowed_Id(Long followedId);
    List<Following> findAllByFollowed_Id(Long followedId);
    void deleteAllByFollowed_IdOrFollower_Id(Long followedId, Long followerId);
  }
