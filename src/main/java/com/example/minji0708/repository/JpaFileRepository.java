package com.example.minji0708.repository;

import com.example.minji0708.domain.MyFile;
import com.example.minji0708.domain.MyImage;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaFileRepository implements FileRepository {

    private EntityManager em;

    public JpaFileRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public MyFile saveFile(MyFile file) {
        em.persist(file);
        return file;
    }

    @Override
    public Optional<MyFile> findByFileNo(Long no) {
        MyFile file = em.find(MyFile.class, no);
        return Optional.ofNullable(file);
    }

    @Override
    public List<MyFile> findByLibraryNo(Long library_no) {
        return em.createQuery("select m from MyFile m where " +
                "m.library.no = :library_no", MyFile.class)
                .setParameter("library_no", library_no)
                .getResultList();
    }
}
