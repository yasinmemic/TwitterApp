package com.opthema.twitter.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
public class Tweet extends AbstractEntity implements Serializable {

    @Column(nullable = false)
    private String content;


    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private Users user;

    private int rtCount;
    private int likedCount;
}
