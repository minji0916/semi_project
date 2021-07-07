package semi.searchTestspring.repository;

import semi.searchTestspring.domain.Image;
import semi.searchTestspring.domain.Library;

import java.util.List;
import java.util.Optional;

public interface ImageRepository {
    Image saveImage(Image image);
    Optional<Image> findById(Long img_file_no);
    Optional<Image> findByLibraryId(Long library_id);
    Optional<Image> findByname(String img_name);
    List<Image> findAll();
}
