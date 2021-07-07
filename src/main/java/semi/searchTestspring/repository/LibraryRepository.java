package semi.searchTestspring.repository;

import semi.searchTestspring.domain.Image;
import semi.searchTestspring.domain.Library;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LibraryRepository {
    Library saveLibrary(Library library);
    Optional<Library> findByLibraryId(Long library_id);
    Optional<Library> findByMemberName(String member_name);
    Optional<Library> findByLibraryName(String library_name);
    Optional<Library> findByViews(int views);
    Optional<Library> findByRegistDate(LocalDateTime requestDateTime);
    List<Library> findAll();
}

