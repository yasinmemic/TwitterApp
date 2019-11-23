package com.opthema.twitter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder

public class ReTweet extends AbstractEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User userId;

    @ManyToOne
    @JoinColumn(name ="TWEET_ID")
    private Tweet tweetId;

}
