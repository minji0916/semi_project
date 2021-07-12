package com.example.minji0708.repository;

import com.example.minji0708.domain.MyImage;

import java.util.List;
import java.util.Optional;

public interface ImageRepository {
    MyImage saveImage(MyImage image);
    Optional<MyImage> findByNo(Long no);
    Optional<MyImage> findByLibraryNo(Long library_no);
}
