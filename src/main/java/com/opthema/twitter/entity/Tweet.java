package com.opthema.twitter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Tweet extends AbstractEntity implements Serializable {

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    private int rtCount;
    private int likedCount;
}
