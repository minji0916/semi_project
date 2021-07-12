package com.example.minji0708.service;

import com.example.minji0708.domain.MyLibrary;
import com.example.minji0708.domain.MyMember;
import com.example.minji0708.repository.LibraryRepository;
import com.example.minji0708.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Transactional
public class LibraryService {

    EntityManager em;

    private final LibraryRepository libraryRepository;

    @Autowired
    public LibraryService(LibraryRepository libraryRepository) { // 의존성 주입
        this.libraryRepository = libraryRepository;
    }

    //라이브러명으로 검색
    public Optional<MyLibrary> searchByLibraryName(String library_name){
        return libraryRepository.findByName(library_name);
    }

    //작성자명으로 검색
    public List<MyLibrary> searchByMemberName(String member_name){
        return libraryRepository.findByMemberName(member_name);
    }

    //라이브러리+작성자명으로 검색
    public List<MyLibrary> searchByLibMemName(String name){
        return libraryRepository.findByLibMem(name);
    }

    //library 다찾기 (여러명 나올 것임)
    public List<MyLibrary> findLibraries(){
        return libraryRepository.findAll();
    }

}
