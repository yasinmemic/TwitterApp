package com.opthema.twitter.model;

import com.opthema.twitter.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TweetResponse {

    private Long id;
    private String content;
    private int rtCount;
    private int likedCount;
    private User user;
    private LocalDateTime createdAt;
}
