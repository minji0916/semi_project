package semi.searchTestspring.repository;

import semi.searchTestspring.domain.Image;

import java.util.List;
import java.util.Optional;

public interface ImageRepository {
    Image save(Image image);
    Optional<Image> findById(Long img_file_no);
    Optional<Image> findByname(String img_file_name);
    List<Image> findAll();
}
