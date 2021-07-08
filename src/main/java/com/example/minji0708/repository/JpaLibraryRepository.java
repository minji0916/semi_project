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

    @Override
    public Optional<MyLibrary> findByNo(Long no) {
        MyLibrary library = em.find(MyLibrary.class, no);
        return Optional.ofNullable(library);
    }

    @Override
    public Optional<MyLibrary> findByName(String name) {
        List<MyLibrary> result = em.createQuery("select m from MyLibrary m where " +
                "m.name = :library_name", MyLibrary.class)
                .setParameter("library_name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<MyLibrary> findByViews(int views) {
        return em.createQuery("select m from MyLibrary m where " +
                "m.views = :views", MyLibrary.class)
                .setParameter("views", views)
                .getResultList();
    }

    @Override
    public List<MyLibrary> findByRegistDate(LocalDateTime requestDateTime) {
        return em.createQuery("select m from MyLibrary m where " +
                "m.requestDateTime = :date", MyLibrary.class)
                .setParameter("date", requestDateTime)
                .getResultList();
    }

    @Override
    public List<MyLibrary> findAll() {
        return em.createQuery("select m from MyLibrary m", MyLibrary.class)
                .getResultList();
    }

    @Override
    public Optional<MyLibrary> findByMemberId(String member_id) {
        return Optional.empty();
    }

    @Override
    public Optional<MyLibrary> findByMemberName(String member_name) {
        return Optional.empty();
    }


}