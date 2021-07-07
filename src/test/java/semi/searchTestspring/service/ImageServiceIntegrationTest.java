package semi.searchTestspring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import semi.searchTestspring.domain.Image;
import semi.searchTestspring.domain.Library;
import semi.searchTestspring.repository.ImageRepository;
import semi.searchTestspring.repository.JpaImageRepository;
import semi.searchTestspring.repository.JpaLibraryRepository;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class ImageServiceIntegrationTest {
    @Autowired
    ImageService imageService;
    @Autowired
    JpaImageRepository jpaImageRepository;
    @Autowired
    LibraryService libraryService;
    @Autowired
    JpaLibraryRepository jpaLibraryRepository;


    @Test
    @Rollback(value = false) // 테스트가 끝나도 롤백하고 싶지 않음
    public void 이미지파일명_검색() throws Exception {

        //Given
        // 라이브러리 생성
        Library library = new Library();
        library.setLibrary_name("lib_sample");
        library.setMember_name("minzy");

        // 이미지 생성
        Image image = new Image();
        image.setImg_name("1.jpg");
        image.setLibrary_id(library);

        //When : 라이브러리와 이미지 저장
        Library savedLibrary = jpaLibraryRepository.saveLibrary(library);
        Image savedImage = jpaImageRepository.saveImage(image);

        System.out.println("savedLibrary = " + savedLibrary);
        System.out.println("savedImage = " + savedImage);

        // 이미지 아이디로 이미지 하나 찾아오기
        Optional<Image> findImgById = imageService.findOne(1L);
        System.out.println("findImgById_getImg_name = " + findImgById.get().getImg_name());
        System.out.println("findImgById_getLibrary_id = " + findImgById.get().getLibrary_id());
        System.out.println("findImgById_getImg_id = " + findImgById.get().getImg_id());

        //Then : 저장한 이미지의 아이디로 레포지토리에서 이미지 검색
        Image findImg = jpaImageRepository.findById(savedImage.getImg_id()).get();

        // 이름  비교.
        //assertEquals(savedImage.getImg_name(), findImg.getImg_name());
        assertEquals(findImgById.get().getImg_id(), findImg.getImg_id());
    }
}
