package com.example.minji0708.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "library")
@Getter @Setter
public class MyLibrary {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="library_no")
    private Long no;

    @ManyToOne //라이브러리 여러개에 작성자 하나
    @JoinColumn(name = "member_no")
    private MyMember member;

    @Column(name="library_name")
    private String name;

    private int views; // 조회수

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name="regist_date")
    private LocalDateTime requestDateTime; //등록일
}
