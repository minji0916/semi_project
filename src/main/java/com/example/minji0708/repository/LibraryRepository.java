package com.example.minji0708.repository;

import com.example.minji0708.domain.MyLibrary;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LibraryRepository {
    //라이브러리 저장
    MyLibrary saveLibrary(MyLibrary library);

    //라이브러리 no로 찾을 때
    Optional<MyLibrary> findByNo(Long no);

    //라이브러리명으로 찾을 때
    Optional<MyLibrary> findByName(String name);

    //조회수 몇 이상 이렇게 찾을 때
    List<MyLibrary> findByViews(int views);

    //최신 등록순 이렇게 찾을 때
    List<MyLibrary> findByRegistDate(LocalDateTime requestDateTime);

    //다 찾을 때
    List<MyLibrary> findAll();

    //FK 사용
    // member 들어와서 라이브러리 찾을 때
    Optional<MyLibrary> findByMemberId(String member_id);

    // 작성자 이름이 들어와서 라이브러리 찾을 때
    Optional<MyLibrary> findByMemberName(String member_name);
}
