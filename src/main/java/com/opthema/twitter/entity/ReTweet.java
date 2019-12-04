package com.opthema.twitter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(uniqueConstraints=
@UniqueConstraint(columnNames={"USER_ID", "TWEET_ID"}))
public class ReTweet extends AbstractEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private Users userId;

    @ManyToOne
    @JoinColumn(name ="TWEET_ID")
    private Tweet tweetId;

}
