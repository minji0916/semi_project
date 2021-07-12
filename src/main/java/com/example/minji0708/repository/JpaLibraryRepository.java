package com.example.minji0708.repository;

import com.example.minji0708.domain.MyLibrary;
import com.example.minji0708.domain.MyMember;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class JpaLibraryRepository implements LibraryRepository{
    private EntityManager em;
    public JpaLibraryRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public MyLibrary saveLibrary(MyLibrary library) {
        em.persist(library);
        return library;
    }

    @Override //ex) library1인데 lib만 검색해도 목록이 나와야한다면 아래 주석처럼 바꾸고, Optional을 List로 바꾸기
    public Optional<MyLibrary> findByName(String name) {
//        return em.createQuery("select l " +
//                "from MyLibrary l " +
//                "where l.name LIKE :name", MyLibrary.class)
//                .setParameter("name", '%'+name+'%')
//                .getResultList();
        
        List<MyLibrary> result = em.createQuery("select m from MyLibrary m where " +
                "m.name = :library_name", MyLibrary.class)
                .setParameter("library_name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<MyLibrary> findByMemberName(String member_name) {
        return em.createQuery("select m from MyLibrary m where " +
                "m.member.name = :member_name", MyLibrary.class)
                .setParameter("member_name", member_name)
                .getResultList();
    }

    @Override
    public List<MyLibrary> findByLibMem(String name) {
        return em.createQuery("select l " +
                "from MyLibrary l " +
                "join fetch l.member " +
                "where l.name like :name or " +
                "l.member.name like :name", MyLibrary.class)
                .setParameter("name",  '%'+name+'%')
                .getResultList();
    }

    @Override
    public List<MyLibrary> findAll() {
        return em.createQuery("select m from MyLibrary m", MyLibrary.class)
                .getResultList();
    }
}



    //    @Override
//    public Optional<MyLibrary> findByNo(Long no) {
//        MyLibrary library = em.find(MyLibrary.class, no);
//        return Optional.ofNullable(library);
//    }
//
//    @Override
//    public List<MyLibrary> findByViews(int views) {
//        return em.createQuery("select m from MyLibrary m where " +
//                "m.views = :views", MyLibrary.class)
//                .setParameter("views", views)
//                .getResultList();
//    }
//
//    @Override
//    public List<MyLibrary> findByRegistDate(LocalDateTime requestDateTime) {
//        return em.createQuery("select m from MyLibrary m where " +
//                "m.requestDateTime = :date", MyLibrary.class)
//                .setParameter("date", requestDateTime)
//                .getResultList();
//    }
//
//
//
//    @Override
//    public Optional<MyLibrary> findByMemberId(String member_id) {
//        return Optional.empty();
//    }





