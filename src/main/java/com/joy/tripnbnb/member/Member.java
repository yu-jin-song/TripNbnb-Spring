package com.joy.tripnbnb.member;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", unique = true, nullable = false)
    private Long id;

    @Column
    private String memberName;

    @Column
    private String email;

    @Column
    private String profileImg;


    @Builder
    public Member(String memberName, String email, String profileImg) {
        this.memberName = memberName;
        this.email = email;
        this.profileImg = profileImg;
    }

}
