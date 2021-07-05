package semi.searchTestspring.repository;

import semi.searchTestspring.domain.Library;

import java.util.List;
import java.util.Optional;

public interface LibraryRepository {
    Library save(Library library);
    Optional<Library> findById(Long library_no);
    Optional<Library> findByMemberName(String member_name);
    Optional<Library> findByLibraryName(String library_name);
    Optional<Library> findByViews(int views);
    Optional<Library> findByRegistDate(String regist_date);
    List<Library> findAll();
}

