package semi.searchTestspring.repository;

import semi.searchTestspring.domain.Image;
import semi.searchTestspring.domain.Library;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaImageRepository implements ImageRepository {

    private EntityManager em;

    public JpaImageRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Image saveImage(Image image) {
        em.persist(image);
        return image;
    }

    @Override
    public Optional<Image> findById(Long img_id) {
        Image image = em.find(Image.class, img_id);
        return Optional.ofNullable(image);
    }

    @Override
    public Optional<Image> findByLibraryId(Long library_id) {
        Image image = em.find(Image.class, library_id);
        return Optional.ofNullable(image);
    }

    @Override // 이미지 이름으로 찾기
    public Optional<Image> findByname(String img_name) { // 테이블명 주의
        List<Image> result = em.createQuery("select m from Image m where" +
                "m.img_name = :img_name", Image.class)
                .setParameter("img_name", img_name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Image> findAll() { // 테이블명 주의
        return em.createQuery("select m from Image m", Image.class)
                .getResultList();
    }
}
