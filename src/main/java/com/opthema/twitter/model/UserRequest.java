package com.opthema.twitter.model;


import lombok.*;

import javax.persistence.Column;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private String imgUrl;
}
