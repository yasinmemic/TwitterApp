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
public class Following extends AbstractEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "FOLLOWER_ID", nullable = false)
    private User follower;

    @ManyToOne
    @JoinColumn(name = "FOLLOWED_ID", nullable = false)
    private User followed;

    private boolean isAccepted;

}
