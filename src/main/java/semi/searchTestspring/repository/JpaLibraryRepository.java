package semi.searchTestspring.repository;

import semi.searchTestspring.domain.Library;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaLibraryRepository implements LibraryRepository{

    private EntityManager em;

    public JpaLibraryRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Library save(Library library) {
        em.persist(library);
        return library;
    }

    @Override
    public Optional<Library> findById(Long library_no) {
        Library library = em.find(Library.class, library_no);
        return Optional.ofNullable(library);
    }

    @Override
    public Optional<Library> findByMemberName(String member_name) {
        List<Library> result = em.createQuery("select m from BIM_CONTENTS m where " +
                "m.name = :name", Library.class)
                .setParameter("name", member_name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public Optional<Library> findByLibraryName(String library_name) {
        return Optional.empty();
    }

    @Override
    public Optional<Library> findByViews(int views) {
        Library library = em.find(Library.class, views);
        return Optional.ofNullable(library);
    }

    @Override
    public Optional<Library> findByRegistDate(String regist_date) {
        return Optional.empty();
    }

    @Override
    public List<Library> findAll() {
        return em.createQuery("select m from BIM_CONTENTS m", Library.class)
                .getResultList();
    }
}
