package semi.searchTestspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import semi.searchTestspring.domain.Image;
import semi.searchTestspring.repository.ImageRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public class ImageService {
    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    /**
     * 전체 회원 조회
     */
    public List<Image> findMembers() {
        return imageRepository.findAll();
    }
    public Optional<Image> findOne(Long memberId) {
        return imageRepository.findById(memberId);
    }
}
