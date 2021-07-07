package semi.searchTestspring.repository;

import semi.searchTestspring.domain.Library;

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
    public Library saveLibrary(Library library){ // 임시로 만든 메소드
        em.persist(library);
        return library;
    }

    @Override
    public Optional<Library> findByLibraryId(Long library_no) {
        Library library = em.find(Library.class, library_no);
        return Optional.ofNullable(library);
    }

    @Override
    public Optional<Library> findByMemberName(String member_name) {
        List<Library> result = em.createQuery("select m from Library m where " +
                "m.member_name = :member_name", Library.class)
                .setParameter("member_name", member_name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public Optional<Library> findByLibraryName(String library_name) {
        List<Library> result = em.createQuery("select m from Library m where " +
                "m.library_name = :library_name", Library.class)
                .setParameter("library_name", library_name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public Optional<Library> findByViews(int views) {
        Library library = em.find(Library.class, views);
        return Optional.ofNullable(library);
    }

    @Override
    public Optional<Library> findByRegistDate(LocalDateTime requestDateTime) {
        Library library = em.find(Library.class, requestDateTime);
        return Optional.ofNullable(library);
    }

    @Override
    public List<Library> findAll() {
        return em.createQuery("select m from Library m", Library.class)
                .getResultList();
    }
}
