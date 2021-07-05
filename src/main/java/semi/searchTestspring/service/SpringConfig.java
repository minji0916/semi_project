package semi.searchTestspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import semi.searchTestspring.domain.Library;
import semi.searchTestspring.repository.ImageRepository;
import semi.searchTestspring.repository.JpaLibraryRepository;
import semi.searchTestspring.repository.LibraryRepository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final ImageRepository imageRepository;

    @Autowired
    public SpringConfig(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Bean
    public ImageService imageService() {
        return new ImageService(imageRepository);
    }
//    @Bean
//    public LibraryRepository libraryRepository() {
//        return new JpaLibraryRepository(em);
//    }
}