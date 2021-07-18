package semi.minji0718;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import semi.minji0718.repository.*;
import semi.minji0718.service.FileService;
import semi.minji0718.service.ImageService;
import semi.minji0718.service.LibraryService;
import semi.minji0718.service.MemberService;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private final EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService(EntityManager em){
        return new MemberService(new JpaMemberRepository(em));
    }
    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);
    }

    @Bean
    public LibraryService libraryService(EntityManager em){
        return new LibraryService(new JpaLibraryRepository(em));
    }
    @Bean
    public LibraryRepository libraryRepository() { return new JpaLibraryRepository(em);
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
    public FileService fileService(EntityManager em) { // em 의존성주입
        return new FileService(new JpaFileRepository(em));
    }
    @Bean
    public FileRepository fileRepository(){
        return new JpaFileRepository(em);
    }
}
