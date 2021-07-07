package semi.searchTestspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import semi.searchTestspring.domain.File;
import semi.searchTestspring.domain.Image;
import semi.searchTestspring.domain.Library;
import semi.searchTestspring.repository.FileRepository;
import semi.searchTestspring.repository.ImageRepository;
import semi.searchTestspring.repository.JpaFileRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Transactional // @Transactional 애노테이션 : "데이터 변경"이 필요하므로 적어주기.
public class FileService {

    EntityManager em;

    private final JpaFileRepository jpaFileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) { // 의존성 주입
        this.fileRepository = fileRepository;
    }

    //전체 조회
    public List<File> findAllFiles() {
        return fileRepository.findAll();
    }

    //file_id로 검색
    public Optional<File> findFileId(Long file_id) {
        return fileRepository.findByFileId(file_id);
    }

    //file_name으로 검색
    public Optional<File> findFileName(String file_name) {
        return fileRepository.findByFilename(file_name);
    }

    //library_id로 file 검색

    public List<File> findLibraryFile(Library library) {
        return fileRepository.findByLibraryId(library);
    }
}