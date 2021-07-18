package semi.minji0718.repository;

import semi.minji0718.domain.MyFile;

import java.util.List;
import java.util.Optional;

public interface FileRepository {

    MyFile saveFile(MyFile file);
    Optional<MyFile> findByFileNo(Long no);
    List<MyFile> findByLibraryNo(Long library_no);
}
