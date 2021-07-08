package com.example.minji0708.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Member")
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
}
