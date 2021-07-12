package com.example.minji0708.repository;

import com.example.minji0708.domain.MyLibrary;
import com.example.minji0708.domain.MyMember;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/** 라이브러리 검색 : 작성자, 라이브러리 명 , 작성자+라이브러리명 3가지.
 *
 * 정렬 : 라이브러리명 오름차순 내림차순. 등록일 오름차순 내림차순.
 */
public interface LibraryRepository {

    //라이브러리 저장
    MyLibrary saveLibrary(MyLibrary library);

    //라이브러리명으로 찾을 때
    Optional<MyLibrary> findByName(String name);

    // 작성자 이름이 들어와서 라이브러리 찾을 때
    List<MyLibrary> findByMemberName(String member_name);

    // 작성자 + 라이브러리명
    // 예시 ) 민지
    // 작성자에 민지가 들어간 라이브러리들. + 라이브러리 명에 민지가 들어간 라이브러리 들.  모두 리턴.
    // where    name   or  library_name like "%minji%"
    List<MyLibrary> findByLibMem(String name);

    List<MyLibrary> findAll();
}
