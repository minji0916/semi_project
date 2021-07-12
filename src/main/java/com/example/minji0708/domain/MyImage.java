package com.example.minji0708.domain;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "img_path")
@Getter @Setter
public class MyImage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="img_file_no")
    private Long no;

    @OneToOne //라이브러리 : 미리보기 이미지 = 1 : 1
    @JoinColumn(name = "library_no")
    private MyLibrary library;

    @ManyToOne //이미지 여러개에 작성자 하나
    @JoinColumn(name = "member_no")
    private MyMember member;

    @Column(name="img_file_name")
    private String name;

    @Enumerated(EnumType.STRING)
    private ImageStatusFlag status_flag;
}
