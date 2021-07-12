package com.example.minji0708.repository;

import com.example.minji0708.domain.MyMember;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{ // 순수 Jpa , 인터페이스로 쿼리메소드를 쓰는건 데이터 jpa .

    private EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public MyMember saveMember(MyMember member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<MyMember> findByNo(Long no) {
        MyMember member = em.find(MyMember.class, no);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<MyMember> findById(String id) { //table명 : 대소문자 주의      db랑 관계없이 java class에 있는 domain이랑 변수명만 사용해야 함
        List<MyMember> result = em.createQuery("select m from MyMember m where " +
                "m.id = :member_id", MyMember.class)
                .setParameter("member_id", id)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<MyMember> findByName(String name) {
        return em.createQuery("select m from MyMember m where " +
                "m.name = :member_name", MyMember.class)
                .setParameter("member_name", name)
                .getResultList();
    }
}

//    @Override
//    public List<MyMember> findAll() {
//        return em.createQuery("select m from MyMember m", MyMember.class)
//                .getResultList();
//    }
//
//    @Override
//    public List<MyMember> findByLevel(String level) {
//        return em.createQuery("select m from MyMember m where " +
//                "m.level = :member_level", MyMember.class)
//                .setParameter("member_level", level)
//                .getResultList();
//    }

    //이름 중복 안될 때
//    @Override
//    public Optional<MyMember> findByName(String name) {
//        List<MyMember> result = em.createQuery("select m from MyMember m where " +
//                "m.name = :member_name", MyMember.class)
//                .setParameter("member_name", name)
//                .getResultList();
//        return result.stream().findAny();
//    }





