package com.example.minji0708.repository;

import com.example.minji0708.domain.MyFile;
import com.example.minji0708.domain.MyLibrary;

import java.util.List;
import java.util.Optional;

public interface FileRepository {
    MyFile saveFile(MyFile file);
    Optional<MyFile> findByFileNo(Long no);
    List<MyFile> findByLibraryNo(Long library_no);
}
