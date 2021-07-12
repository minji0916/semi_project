package com.example.minji0708.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter @Setter
public class MyMember {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_no")
    @NonNull
    private Long no;

    @NonNull
    @Column(name="member_id")
    private String id;

    @NonNull
    @Column(name = "member_level")
    private String level;

    @NonNull
    @Column(name = "member_name")
    private String name;

    /**
     * 데이터베이스 테이블, 칼럼명 전부 소문자로.
     * 단어 간의 연결은 _
     * 카멜케이스 잘 안씀 . MemberRepo
     *
     * */
}
