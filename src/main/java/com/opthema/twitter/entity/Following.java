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
@UniqueConstraint(columnNames={"FOLLOWER_ID", "FOLLOWED_ID"}))
public class Following extends AbstractEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name = "FOLLOWER_ID", nullable = false)
    private Users follower;

    @ManyToOne
    @JoinColumn(name = "FOLLOWED_ID", nullable = false)
    private Users followed;

    private boolean isAccepted;
}
