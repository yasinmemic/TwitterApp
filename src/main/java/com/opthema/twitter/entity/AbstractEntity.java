package com.opthema.twitter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class) // asagidaki tarihle ilgili anotasyonları calistirmak icin bir listener

public abstract class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    @CreatedDate
    private LocalDateTime createdAt;

//    Authentication auth;
//    User userid= (User) auth.getPrincipal();
//    Long userIDDD= userid.getId();

    @PrePersist
    void onCreate() {
        setCreatedAt(LocalDateTime.now());
    }

}
