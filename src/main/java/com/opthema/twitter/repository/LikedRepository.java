package com.opthema.twitter.repository;

import com.opthema.twitter.entity.LikedTweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikedRepository extends JpaRepository<LikedTweet,Long> {

}
