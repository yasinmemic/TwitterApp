package com.opthema.twitter.model;

import com.opthema.twitter.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TweetRequest {

    private String content;
    private int rtCount;
    private int likedCount;
}