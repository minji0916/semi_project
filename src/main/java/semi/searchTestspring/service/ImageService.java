package semi.searchTestspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import semi.searchTestspring.domain.Image;
import semi.searchTestspring.domain.Library;
import semi.searchTestspring.repository.ImageRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Transactional // @Transactional 애노테이션 : "데이터 변경"이 필요하므로 적어주기.
public class ImageService {

    EntityManager em;

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) { // 의존성 주입
        this.imageRepository = imageRepository;
    }

    /**
     * 전체 이미지 조회
     */
    public List<Image> findImages() {
        return imageRepository.findAll();
    }
    public Optional<Image> findOne(Long imageId) {
        return imageRepository.findById(imageId);
    }
    public Optional<Image> findLibraryImage(Long library_id){
        return imageRepository.findByLibraryId(library_id);
    }
}
