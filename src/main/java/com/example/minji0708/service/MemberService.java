package com.example.minji0708.service;

import com.example.minji0708.domain.MyMember;
import com.example.minji0708.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    EntityManager em;

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) { // 의존성 주입
        this.memberRepository = memberRepository;
    }

    //member_no로 member 찾기
    public Optional<MyMember> findMemberNo(Long no){
        return memberRepository.findByNo(no);
    }

    //member_id로 member 찾기
    public Optional<MyMember> findMemberId(String id){
        return memberRepository.findById(id);
    }

    //member_name 으로 member 찾기 (아이디 중복 될 때)
    public List<MyMember> findMemberName(String name){
        return memberRepository.findByName(name);
    }

    //member 다찾기 (여러명 나올 것임)
    //public List<MyMember> findMembers(){
//        return memberRepository.findAll();
//    }

    //member_name 으로 member 찾기 (아이디 중복 안될 때)
//    public Optional<MyMember> findMemberName(String name){
//        return memberRepository.findByName(name);
//    }

    //member_level로 member 찾기 (여러명 나올 것임)
//    public List<MyMember> findMemberLevel(String level) {
//        return memberRepository.findByLevel(level);
//    }
}
