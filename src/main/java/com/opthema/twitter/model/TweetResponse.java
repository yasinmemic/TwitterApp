package com.opthema.twitter.model;

import com.opthema.twitter.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TweetResponse implements Comparable<TweetResponse>{

    private Long id;
    private String content;
    private int rtCount;
    private int likedCount;
    private Users user;
    private LocalDateTime createdAt;

    @Override
    public int compareTo(TweetResponse o) {
        if (this.id < o.id) {
            return 1;
        } else if (this.id == o.id) {
            return 0;
        } else
            return -1;
    }
}
