package semi.searchTestspring.repository;

import semi.searchTestspring.domain.File;
import semi.searchTestspring.domain.FileStatusFlag;
import semi.searchTestspring.domain.FileTypeFlag;
import semi.searchTestspring.domain.Library;

import java.util.Optional;
import java.util.List;

public interface FileRepository {
    File saveFile(File file);
    Optional<File> findByFileId(Long file_id);
    Optional<File> findByFilename(String file_name);
//    Optional<File> findByFileStatusFlag(FileStatusFlag fileStatusFlag);
//    Optional<File> findByFileTypeFlag(FileTypeFlag fileTypeFlag);
    List<File> findByLibraryId(Library library);
    List<File> findAll();
}
