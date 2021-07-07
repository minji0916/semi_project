package semi.searchTestspring.repository;

import semi.searchTestspring.domain.File;
import semi.searchTestspring.domain.FileStatusFlag;
import semi.searchTestspring.domain.FileTypeFlag;
import semi.searchTestspring.domain.Library;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JpaFileRepository implements FileRepository {

    private EntityManager em;

    public JpaFileRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public File saveFile(File file) {
        em.persist(file);
        return file;
    }

    @Override
    public Optional<File> findByFileId(Long file_id) {
        File file = em.find(File.class, file_id);
        return Optional.ofNullable(file);
    }

    @Override
    public Optional<File> findByFilename(String file_name) {
        List<File> result = em.createQuery("select m from File m where" +
                "m.file_name = :file_name", File.class)
                .setParameter("file_name", file_name)
                .getResultList();
        return result.stream().findAny();
    }

//    @Override
//    public Optional<File> findByFileStatusFlag(FileStatusFlag fileStatusFlag) {
//        List<File> result = em.createQuery("select m from File m where" +
//                "m.fileStatusFlag = :fileStatusFlag", File.class)
//                .setParameter("fileStatusFlag", fileStatusFlag)
//                .getResultList();
//        return result.stream().findAny();
//    }
//
//    @Override
//    public Optional<File> findByFileTypeFlag(FileTypeFlag fileTypeFlag) {
//        List<File> result = em.createQuery("select m from File m where" +
//                "m.fileTypeFlag = :fileTypeFlag", File.class)
//                .setParameter("fileTypeFlag", fileTypeFlag)
//                .getResultList();
//        return result.stream().findAny();
//    }

    @Override
    public List<File> findByLibraryId(Library library) {
//        TypedQuery<File> query = em.createQuery("select m from File m where m.library_id = :library_id", File.class)
//                .setParameter("library_id", library_id);
//        List<File> result = query.getResultList();
//
//        return result;

        return em.createQuery("select m from File m where " +
                "m.library_id = :library_id", File.class)
                .setParameter("library_id", library.getLibrary_id())
                .getResultList();
    }

    @Override
    public List<File> findAll() {
        return em.createQuery("select m from File m", File.class)
                .getResultList();
    }
}
