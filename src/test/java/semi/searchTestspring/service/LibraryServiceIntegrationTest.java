package semi.searchTestspring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import semi.searchTestspring.domain.Image;
import semi.searchTestspring.domain.Library;
import semi.searchTestspring.repository.JpaImageRepository;
import semi.searchTestspring.repository.JpaLibraryRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class LibraryServiceIntegrationTest {
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
    public void 라이브러리_이미지_저장() throws Exception {

        //Given
        // 라이브러리 생성
        Library library = new Library();
        library.setLibrary_name("new_library");
        library.setMember_name("jia");
        library.setViews(10);
        library.setRequestDateTime(LocalDateTime.now());

        // 이미지 생성
        Image image = new Image();
        image.setImg_name("jia.jpg");
        image.setLibrary_id(library);

        //When : 라이브러리와 이미지 저장
        Library savedLibrary = jpaLibraryRepository.saveLibrary(library);
        Image savedImage = jpaImageRepository.saveImage(image);

        System.out.println("savedLibrary = " + savedLibrary);
        System.out.println("savedLibrary_library_id = " + savedLibrary.getLibrary_id());
        System.out.println("savedLibrary_library_name = " + savedLibrary.getLibrary_name());
        System.out.println("savedLibrary_member_name = " + savedLibrary.getMember_name());
        System.out.println("savedLibrary_views = " + savedLibrary.getViews());
        System.out.println("savedLibrary_date = " + savedLibrary.getRequestDateTime());
        System.out.println("\n");
        System.out.println("savedImage = " + savedImage);
        System.out.println("savedImage_library_id = " + savedImage.getLibrary_id());
        System.out.println("savedImage_image_name = " + savedImage.getImg_name());
    }

    @Test
    @Rollback(value = false) // 테스트가 끝나도 롤백하고 싶지 않음
    public void 라이브러리명_검색() throws Exception {  //사용자가 라이브러리명으로 검색하면 가져옴
        //Given
        // 라이브러리 생성
        Library library = new Library();
        library.setLibrary_name("new_library2");
        library.setMember_name("newMem2");
        library.setViews(1);
        library.setRequestDateTime(LocalDateTime.now());

        // 이미지 생성
        Image image = new Image();
        image.setImg_name("newMem2.jpg");
        image.setLibrary_id(library);

        //When : 라이브러리와 이미지 저장
        Library savedLibrary = jpaLibraryRepository.saveLibrary(library);
        Image savedImage = jpaImageRepository.saveImage(image);

        // 서비스에서 라이브러리명으로 라이브러리 하나 가져오기
        Optional<Library> findByLibName = libraryService.findLibrary("new_library2");

        //Then
        // 레포지토리에서 저장한 라이브러리명으로 라이브러리 검색
        Library findRepLib = jpaLibraryRepository.findByLibraryName(savedLibrary.getLibrary_name()).get();
        Image findRepImg = jpaImageRepository.findByLibraryId(findRepLib.getLibrary_id()).get();

        // 이름 비교 시 맞으면 에러없음 -> 아래사항 출력
        // 1. 라이브러리명
        // 2. 작성자
        // 3. 미리보기 이미지
        // 4. 조회수
        // 5. 등록일
        assertEquals(findByLibName.get().getLibrary_name(), findRepLib.getLibrary_name());

        System.out.println("library_name = " + findRepLib.getLibrary_name());
        System.out.println("member_name = " + findRepLib.getMember_name());
        System.out.println("image = " + findRepImg.getImg_name());
        System.out.println("views = " + findRepLib.getViews());
        System.out.println("date = " + findRepLib.getRequestDateTime());

    }

}
