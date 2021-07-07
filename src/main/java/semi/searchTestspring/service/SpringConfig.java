package semi.searchTestspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import semi.searchTestspring.domain.Library;
import semi.searchTestspring.repository.*;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public ImageService imageService(EntityManager em) { // em 의존성주입
        return new ImageService(new JpaImageRepository(em));
    }
    @Bean
    public ImageRepository imageRepository(){
        return new JpaImageRepository(em);
    }

    @Bean
    public LibraryService libraryService(EntityManager em) { // em 의존성주입
        return new LibraryService(new JpaLibraryRepository(em));
    }
    @Bean
    public LibraryRepository libraryRepository() {
        return new JpaLibraryRepository(em);
    }

    @Bean
    public FileService fileService(EntityManager em) { // em 의존성주입
        return new FileService(new JpaFileRepository(em));
    }
    @Bean
    public FileRepository fileRepository() {
        return new JpaFileRepository(em);
    }
}