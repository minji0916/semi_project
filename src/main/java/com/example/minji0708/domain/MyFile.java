package com.example.minji0708.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "file_path")
@Getter
@Setter
public class MyFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="file_no")
    private Long no;

    @ManyToOne //파일 여러개에 라이브러리 하나
    @JoinColumn(name = "library_no")
    private MyLibrary library;

    @Column(name="file_name")
    private String name;

    @Enumerated(EnumType.STRING)
    private FileStatusFlag status_flag;

    @Enumerated(EnumType.STRING)
    private FileTypeFlag file_type;
}
