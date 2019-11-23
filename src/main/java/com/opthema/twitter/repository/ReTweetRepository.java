package com.opthema.twitter.repository;

import com.opthema.twitter.entity.ReTweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReTweetRepository extends JpaRepository<ReTweet,Long> {

}
