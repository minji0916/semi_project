package semi.minji0718.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import semi.minji0718.domain.MyImage;
import semi.minji0718.domain.MyLibrary;
import semi.minji0718.repository.ImageRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Transactional
public class ImageService {
    EntityManager em;

    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) { // 의존성 주입
        this.imageRepository = imageRepository;
    }

    //라이브러리명이 들어왔을 때 조회
    public Optional<MyImage> findLibraryImage(MyLibrary library){
        Long library_no = library.getNo();
        return imageRepository.findByLibraryNo(library_no);
    }
}
