package com.opthema.twitter.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Users")
public class User extends AbstractEntity implements Serializable {
    public static final long serialVersionUID = -1;

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String userName;
    @Column(unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

}
