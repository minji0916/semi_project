package semi.searchTestspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import semi.searchTestspring.domain.Image;
import semi.searchTestspring.domain.Library;
import semi.searchTestspring.repository.ImageRepository;
import semi.searchTestspring.repository.LibraryRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Transactional
public class LibraryService {
    EntityManager em;

    private final LibraryRepository libraryRepository;

    @Autowired
    public LibraryService(LibraryRepository libraryRepository) { // 의존성 주입
        this.libraryRepository = libraryRepository;
    }

    //전체 조회
    public List<Library> findLibraries() {
        return libraryRepository.findAll();
    }

    //작성자명으로 조회
    public Optional<Library> findMember(String member_name) {
        return libraryRepository.findByMemberName(member_name);
    }

    //라이브러리명으로 조회
    public Optional<Library> findLibrary(String library_name) {
        return libraryRepository.findByLibraryName(library_name);
    }
}
