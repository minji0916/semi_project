package semi.searchTestspring.repository;

import semi.searchTestspring.domain.Image;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaImageRepository implements ImageRepository {

    private EntityManager em;

    public JpaImageRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Image save(Image image) {
        em.persist(image);
        return image;
    }

    @Override
    public Optional<Image> findById(Long img_file_no) {
        Image image = em.find(Image.class, img_file_no);
        return Optional.ofNullable(image);
    }

    @Override
    public Optional<Image> findByname(String img_file_name) {
        List<Image> result = em.createQuery("select m from Member m where" +
                "m.name = :name", Image.class)
                .setParameter("name", img_file_name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Image> findAll() {
        return em.createQuery("select m from Member m", Image.class)
                .getResultList();
    }
}
