package semi.minji0718.repository;

import semi.minji0718.domain.MyImage;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaImageRepository implements ImageRepository{

    private EntityManager em;

    public JpaImageRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public MyImage saveImage(MyImage image) {
        em.persist(image);
        return image;
    }

    @Override //이미지만 단독으로 찾을 때
    public Optional<MyImage> findByNo(Long no) {
        MyImage image = em.find(MyImage.class, no);
        return Optional.ofNullable(image);
    }

    @Override //라이브러리 번호로 검색
    public Optional<MyImage> findByLibraryNo(Long library_no) {
        MyImage image = em.find(MyImage.class, library_no);
        return Optional.ofNullable(image);
    }

}
