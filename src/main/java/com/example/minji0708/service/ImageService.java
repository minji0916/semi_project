package com.example.minji0708.service;

import com.example.minji0708.domain.MyImage;
import com.example.minji0708.domain.MyLibrary;
import com.example.minji0708.repository.ImageRepository;
import org.apache.tomcat.jni.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Transactional
public class ImageService {
    EntityManager em;

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) { // 의존성 주입
        this.imageRepository = imageRepository;
    }

    //라이브러리명이 들어왔을 때 조회
    public Optional<MyImage> findLibraryImage(MyLibrary library){
        Long library_no = library.getNo();
        return imageRepository.findByLibraryNo(library_no);
    }
}
