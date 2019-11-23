package com.opthema.twitter.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


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
