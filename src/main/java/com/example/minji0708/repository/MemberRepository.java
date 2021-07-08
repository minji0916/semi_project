package com.example.minji0708.repository;

import com.example.minji0708.domain.MyMember;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    MyMember saveMember(MyMember member);
    Optional<MyMember> findByNo(Long no);
    Optional<MyMember> findById(String id);
    List<MyMember> findAll();
    List<MyMember> findByLevel(String level);

    /** 이름은 중복 가능 */
    //이름 중복이 안될 때
//    Optional<MyMember> findByName(String name);

    //이름 중복될 때
    List<MyMember> findByName(String name);
}
