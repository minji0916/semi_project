package semi.searchTestspring.service;

import org.springframework.data.jpa.repository.JpaRepository;
import semi.searchTestspring.domain.Image;
import semi.searchTestspring.repository.ImageRepository;

import java.util.Optional;

public interface SpringDataJpaImageRepository extends JpaRepository<Image, Long>, ImageRepository {

    @Override
    Optional<Image> findByname(String img_file_name);
}
