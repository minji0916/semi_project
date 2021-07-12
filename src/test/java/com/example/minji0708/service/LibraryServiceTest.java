package com.example.minji0708.service;

import com.example.minji0708.domain.MyLibrary;
import com.example.minji0708.domain.MyMember;
import com.example.minji0708.repository.JpaLibraryRepository;
import com.example.minji0708.repository.JpaMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LibraryServiceTest {
    @Autowired
    LibraryService libraryService;
    @Autowired
    JpaMemberRepository jpaMemberRepository;
    @Autowired
    JpaLibraryRepository jpaLibraryRepository;

    @Test
    @Rollback(value = false)
    public void give_member2_library4() throws Exception{
        //Given
        // member1 생성
        MyMember member1 = new MyMember();
        member1.setId("id_1");
        member1.setLevel("level_1");
        member1.setName("jia");

        // member2 생성
        MyMember member2 = new MyMember();
        member2.setId("id_2");
        member2.setLevel("level_2");
        member2.setName("minji");

        // library1 생성
        MyLibrary library1 = new MyLibrary();
        library1.setMember(member1);
        library1.setName("jia_library");
        library1.setViews(10);
        library1.setRequestDateTime(LocalDateTime.now());

        // library2 생성
        MyLibrary library2 = new MyLibrary();
        library2.setMember(member1);
        library2.setName("not_minji_library");
        library2.setViews(20);
        library2.setRequestDateTime(LocalDateTime.now());

        // library3 생성
        MyLibrary library3 = new MyLibrary();
        library3.setMember(member2);
        library3.setName("minji_library");
        library3.setViews(60);
        library3.setRequestDateTime(LocalDateTime.now());

        // library4 생성
        MyLibrary library4 = new MyLibrary();
        library4.setMember(member2);
        library4.setName("not_jia_library");
        library4.setViews(80);
        library4.setRequestDateTime(LocalDateTime.now());


        //When : memeber1, memeber2, library1, library2, library3, library4 저장
        MyMember savedMember1 = jpaMemberRepository.saveMember(member1);
        MyMember savedMember2 = jpaMemberRepository.saveMember(member2);
        MyLibrary savedLibrary1 = jpaLibraryRepository.saveLibrary(library1);
        MyLibrary savedLibrary2 = jpaLibraryRepository.saveLibrary(library2);
        MyLibrary savedLibrary3 = jpaLibraryRepository.saveLibrary(library3);
        MyLibrary savedLibrary4 = jpaLibraryRepository.saveLibrary(library4);

        List<MyLibrary> findLibAll = libraryService.findLibraries();
        for ( MyLibrary library : findLibAll) {
            System.out.println("라이브러리명: " + library.getName());
            System.out.println("작성자     : " + library.getMember().getName());
            System.out.println("이미지     : " + "추후 추가");
            System.out.println("조회수     : " + library.getViews());
            System.out.println("등록일     : " + library.getRequestDateTime());
        }
    }

    @Test
    @Rollback(value = false)
    public void searchByLibraryName() throws Exception{
        //Then: 라이브러리 명으로 검색
        MyLibrary findLibrary = libraryService.searchByLibraryName("jia_library").get();

        // 출력
        System.out.println("search library = jia_library");
        System.out.println("라이브러리명: " + findLibrary.getName());
        System.out.println("작성자     : " + findLibrary.getMember().getName());
        System.out.println("이미지     : " + "추후 추가");
        System.out.println("조회수     : " + findLibrary.getViews());
        System.out.println("등록일     : " + findLibrary.getRequestDateTime());

    }

    @Test
    @Rollback(value = false)
    public void searchByMemberName() throws Exception{
        //Then: 작성자명으로 검색
        List<MyLibrary> findLibrary = libraryService.searchByMemberName("jia");

        // 출력
        System.out.println("search member = jia");
        for ( MyLibrary library : findLibrary) {
            System.out.println("라이브러리명: " + library.getName());
            System.out.println("작성자     : " + library.getMember().getName());
            System.out.println("이미지     : " + "추후 추가");
            System.out.println("조회수     : " + library.getViews());
            System.out.println("등록일     : " + library.getRequestDateTime());
        }
    }

    @Test
    @Rollback(value = false)
    public void searchByLibMemName() throws Exception{
        //Then: 라이브러리명 + 작성자로 검색
        List<MyLibrary> findLibrary = libraryService.searchByLibMemName("minji");

        // 출력
        System.out.println("search library member = minji");
        for ( MyLibrary library : findLibrary) {
            System.out.println("라이브러리명: " + library.getName());
            System.out.println("작성자     : " + library.getMember().getName());
            System.out.println("이미지     : " + "추후 추가");
            System.out.println("조회수     : " + library.getViews());
            System.out.println("등록일     : " + library.getRequestDateTime());
        }
    }
}