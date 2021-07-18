package semi.minji0718.service;

import semi.minji0718.domain.ImageStatusFlag;
import semi.minji0718.domain.MyImage;
import semi.minji0718.domain.MyLibrary;
import semi.minji0718.domain.MyMember;
import semi.minji0718.repository.JpaImageRepository;
import semi.minji0718.repository.JpaLibraryRepository;
import semi.minji0718.repository.JpaMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
//@Slf4j
class ImageServiceTest {
    @Autowired
    ImageService imageService;
    @Autowired
    JpaImageRepository jpaImageRepository;
    @Autowired
    LibraryService libraryService;
    @Autowired
    JpaLibraryRepository jpaLibraryRepository;
    @Autowired
    JpaMemberRepository jpaMemberRepository;


    @Test
    @Rollback(value = false)
    public void give_member2_library4_image4() throws Exception{
        //Given
        // member1 생성
        MyMember member1 = new MyMember();
        member1.setId("id_1");
        member1.setLevel("level_1");
        member1.setName("jia");

        // member2 생성
        MyMember member2 = new MyMember();
        member2.setId("id_2");
        member2.setLevel("level_2");
        member2.setName("minji");

        // library1 생성
        MyLibrary library1 = new MyLibrary();
        library1.setMember(member1);
        library1.setName("jia_library");
        library1.setViews(10);
        library1.setRequestDateTime(LocalDateTime.now());

        // library2 생성
        MyLibrary library2 = new MyLibrary();
        library2.setMember(member1);
        library2.setName("not_minji_library");
        library2.setViews(20);
        library2.setRequestDateTime(LocalDateTime.now());

        // library3 생성
        MyLibrary library3 = new MyLibrary();
        library3.setMember(member2);
        library3.setName("minji_library");
        library3.setViews(60);
        library3.setRequestDateTime(LocalDateTime.now());

        // library4 생성
        MyLibrary library4 = new MyLibrary();
        library4.setMember(member2);
        library4.setName("not_jia_library");
        library4.setViews(80);
        library4.setRequestDateTime(LocalDateTime.now());

        // image1 생성
        MyImage image1 = new MyImage();
        image1.setLibrary(library1);
        image1.setMember(library1.getMember());
        image1.setName("1.jpg");
        image1.setStatus_flag(ImageStatusFlag.RECENT);

        // image2 생성
        MyImage image2 = new MyImage();
        image2.setLibrary(library2);
        image2.setMember(library2.getMember());
        image2.setName("2.jpg");
        image2.setStatus_flag(ImageStatusFlag.MODIFIED);

        // image3 생성
        MyImage image3 = new MyImage();
        image3.setLibrary(library3);
        image3.setMember(library3.getMember());
        image3.setName("3.jpg");
        image3.setStatus_flag(ImageStatusFlag.DELETED);

        // image4 생성
        MyImage image4 = new MyImage();
        image4.setLibrary(library4);
        image4.setMember(library4.getMember());
        image4.setName("4.jpg");
        image4.setStatus_flag(ImageStatusFlag.RECENT);

        //When : memeber, library, image 저장
        MyMember savedMember1 = jpaMemberRepository.saveMember(member1);
        MyMember savedMember2 = jpaMemberRepository.saveMember(member2);
        MyLibrary savedLibrary1 = jpaLibraryRepository.saveLibrary(library1);
        MyLibrary savedLibrary2 = jpaLibraryRepository.saveLibrary(library2);
        MyLibrary savedLibrary3 = jpaLibraryRepository.saveLibrary(library3);
        MyLibrary savedLibrary4 = jpaLibraryRepository.saveLibrary(library4);
        MyImage savedImage1 = jpaImageRepository.saveImage(image1);
        MyImage savedImage2 = jpaImageRepository.saveImage(image2);
        MyImage savedImage3 = jpaImageRepository.saveImage(image3);
        MyImage savedImage4 = jpaImageRepository.saveImage(image4);

        List<MyLibrary> findLibAll = libraryService.findLibraries();

        for ( MyLibrary library : findLibAll) {
            MyImage image = imageService.findLibraryImage(library).get();
            System.out.println("라이브러리 No : " + library.getNo());
            System.out.println("라이브러리명  : " + library.getName());
            System.out.println("작성자       : " + library.getMember().getName());
            System.out.println("이미지       : " + image.getName());
            System.out.println("조회수       : " + library.getViews());
            System.out.println("등록일       : " + library.getRequestDateTime());
            System.out.println("------------------------------------------------");
        }
    }

    @Test
    public void library1_image_search_Repository() throws Exception {
        // 라이브러리 아이디로 jpaImageRepository에서 직접 검색
        MyImage image = jpaImageRepository.findByLibraryNo(1L).get();
        System.out.println("이미지            : " + image.getName());
        System.out.println("이미지 상태        : " + image.getStatus_flag());
        System.out.println("이미지 라이브러리명  : " + image.getLibrary().getName());
        System.out.println("이미지 작성자명     : " + image.getMember().getName());
    }

    @Test
    public void only_recent_image_search() throws Exception {
        // 라이브러리로 service를 통해 image 검색
        // status_flag == RECENT인 것만 검색
        List<MyLibrary> findLibAll = libraryService.findLibraries();

        for ( MyLibrary library : findLibAll) {
            MyImage image = imageService.findLibraryImage(library).get();

            if (image.getStatus_flag() == ImageStatusFlag.RECENT){
                System.out.println("라이브러리 No : " + library.getNo());
                System.out.println("라이브러리명  : " + library.getName());
                System.out.println("작성자       : " + library.getMember().getName());
                System.out.println("이미지       : " + image.getName());
                System.out.println("조회수       : " + library.getViews());
                System.out.println("등록일       : " + library.getRequestDateTime());
                System.out.println("------------------------------------------------");
            }
        }
    }

}