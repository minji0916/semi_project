package semi.searchTestspring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import semi.searchTestspring.domain.*;
import semi.searchTestspring.repository.JpaFileRepository;
import semi.searchTestspring.repository.JpaImageRepository;
import semi.searchTestspring.repository.JpaLibraryRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class FileServiceIntegrationTest {
    @Autowired
    ImageService imageService;
    @Autowired
    JpaImageRepository jpaImageRepository;
    @Autowired
    LibraryService libraryService;
    @Autowired
    JpaLibraryRepository jpaLibraryRepository;
    @Autowired
    FileService fileService;
    @Autowired
    JpaFileRepository jpaFileRepository;

    @Test
    @Rollback(value = false) // 테스트가 끝나도 롤백하고 싶지 않음
    public void 라이브러리_이미지_첨부파일여러개_저장() throws Exception {

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

        // 첨부파일 생성 - 최신, 엑셀 내부 이미지
        File file1 = new File();
        file1.setFile_name("RECENT:internal_image_file");
        file1.setLibrary_id(library);
        file1.setFileStatusFlag(FileStatusFlag.RECENT);
        file1.setFileTypeFlag(FileTypeFlag.INTERNAL_IMAGE);

        //첨부파일 생성 - 수정됨, 필수첨부파일
        File file2 = new File();
        file2.setFile_name("MODIFIED:Required_image_file");
        file2.setLibrary_id(library);
        file2.setFileStatusFlag(FileStatusFlag.MODIFIED);
        file2.setFileTypeFlag(FileTypeFlag.REQUIRED_FILE);

        //첨부파일 생성 - 삭제됨, 일반첨부파일
        File file3 = new File();
        file3.setFile_name("DELETED:general_image_file_1");
        file3.setLibrary_id(library);
        file3.setFileStatusFlag(FileStatusFlag.DELETED);
        file3.setFileTypeFlag(FileTypeFlag.GENERAL_FILE);

        File file4 = new File();
        file4.setFile_name("DELETED:general_image_file_2");
        file4.setLibrary_id(library);
        file4.setFileStatusFlag(FileStatusFlag.DELETED);
        file4.setFileTypeFlag(FileTypeFlag.GENERAL_FILE);

        File file5 = new File();
        file5.setFile_name("DELETED:general_image_file_3");
        file5.setLibrary_id(library);
        file5.setFileStatusFlag(FileStatusFlag.DELETED);
        file5.setFileTypeFlag(FileTypeFlag.GENERAL_FILE);

        //When : 라이브러리와 이미지 저장
        Library savedLibrary = jpaLibraryRepository.saveLibrary(library);
        Image savedImage = jpaImageRepository.saveImage(image);
        File savedFile1 = jpaFileRepository.saveFile(file1);
        File savedFile2 = jpaFileRepository.saveFile(file2);
        File savedFile3 = jpaFileRepository.saveFile(file3);
        File savedFile4 = jpaFileRepository.saveFile(file4);
        File savedFile5 = jpaFileRepository.saveFile(file5);

        //Then : 결과 확인
        System.out.println("-------------------라이브러리 & 이미지-------------------------");
        System.out.println("savedLibrary      = " + savedLibrary);
        System.out.println("savedLibrary_name = " + savedLibrary.getLibrary_name());
        System.out.println("savedImage_name   = " + savedImage.getImg_name());
        System.out.println("-------------------savedFile1-------------------------");
        System.out.println("savedFile1_id          = " + savedFile1.getFile_id());
        System.out.println("savedFile1_name        = " + savedFile1.getFile_name());
        System.out.println("savedFile1_library_id  = " + savedFile1.getLibrary_id());
        System.out.println("savedFile1_Status_Flag = " + savedFile1.getFileStatusFlag());
        System.out.println("savedFile1_File_Type   = " + savedFile1.getFileTypeFlag());

        System.out.println("-------------------savedFile2-------------------------");
        System.out.println("savedFile2_id          = " + savedFile2.getFile_id());
        System.out.println("savedFile2_name        = " + savedFile2.getFile_name());
        System.out.println("savedFile2_library_id  = " + savedFile2.getLibrary_id());
        System.out.println("savedFile2_Status_Flag = " + savedFile2.getFileStatusFlag());
        System.out.println("savedFile2_File_Type   = " + savedFile2.getFileTypeFlag());

        System.out.println("-------------------savedFile3-------------------------");
        System.out.println("savedFile3_id          = " + savedFile3.getFile_id());
        System.out.println("savedFile3_name        = " + savedFile3.getFile_name());
        System.out.println("savedFile3_library_id  = " + savedFile3.getLibrary_id());
        System.out.println("savedFile3_Status_Flag = " + savedFile3.getFileStatusFlag());
        System.out.println("savedFile3_File_Type   = " + savedFile3.getFileTypeFlag());

        // 전체 첨부파일명 검색
        List<File> findRepFileAll = jpaFileRepository.findAll();
        for ( File f : findRepFileAll) {
            System.out.println("File = " + f.getFile_name());
        }
    }

    @Test
    @Rollback(value = false) 
    public void 첨부파일명_검색() throws Exception {  //사용자가 첨부파일명 검색하면 가져옴
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

        // 첨부파일 생성 - 최신, 엑셀 내부 이미지
        File file1 = new File();
        file1.setFile_name("RECENT:internal_image_file");
        file1.setLibrary_id(library);
        file1.setFileStatusFlag(FileStatusFlag.RECENT);
        file1.setFileTypeFlag(FileTypeFlag.INTERNAL_IMAGE);

        //첨부파일 생성 - 수정됨, 필수첨부파일
        File file2 = new File();
        file2.setFile_name("MODIFIED:Required_image_file");
        file2.setLibrary_id(library);
        file2.setFileStatusFlag(FileStatusFlag.MODIFIED);
        file2.setFileTypeFlag(FileTypeFlag.REQUIRED_FILE);

        //첨부파일 생성 - 삭제됨, 일반첨부파일
        File file3 = new File();
        file3.setFile_name("DELETED:general_image_file1");
        file3.setLibrary_id(library);
        file3.setFileStatusFlag(FileStatusFlag.DELETED);
        file3.setFileTypeFlag(FileTypeFlag.GENERAL_FILE);

        File file4 = new File();
        file4.setFile_name("DELETED:general_image_file_2");
        file4.setLibrary_id(library);
        file4.setFileStatusFlag(FileStatusFlag.DELETED);
        file4.setFileTypeFlag(FileTypeFlag.GENERAL_FILE);

        File file5 = new File();
        file5.setFile_name("DELETED:general_image_file_3");
        file5.setLibrary_id(library);
        file5.setFileStatusFlag(FileStatusFlag.DELETED);
        file5.setFileTypeFlag(FileTypeFlag.GENERAL_FILE);

        //When : 라이브러리와 이미지 저장
        Library savedLibrary = jpaLibraryRepository.saveLibrary(library);
        Image savedImage = jpaImageRepository.saveImage(image);
        File savedFile1 = jpaFileRepository.saveFile(file1);
        File savedFile2 = jpaFileRepository.saveFile(file2);
        File savedFile3 = jpaFileRepository.saveFile(file3);
        File savedFile4 = jpaFileRepository.saveFile(file4);
        File savedFile5 = jpaFileRepository.saveFile(file5);

        // 서비스에서 파일명으로 파일 하나 가져오기 : h2 database error
        //Optional<File> findByLibName = fileService.findFileName("RECENT:internal_image_file");

        // 첨부파일명으로 파일 검색 : ''이든 ""이든 여기선 ERROR
        // DB에 SQL로, SELECT * FROM FILE WHERE FILE_NAME = 'MODIFIED:Required_image_file' 이면 되는데,
        // DB에 SQL로, SELECT * FROM FILE WHERE FILE_NAME = "MODIFIED:Required_image_file" 이면 ERROR
        //File findRepFile = jpaFileRepository.findByFilename('RECENT:internal_image_file').get();

        // 첨부파일 아이디로 파일 검색 : success
        //File findRepFile = jpaFileRepository.findByFileId(savedFile1.getFile_id()).get();

        //Then
        // 레포지토리에서 저장한 라이브러리명으로 라이브러리 검색
        Library findRepLib = jpaLibraryRepository.findByLibraryName(savedLibrary.getLibrary_name()).get();

        // 라이브러리 아이디
        Long library_id = findRepLib.getLibrary_id();
        System.out.println("library_id : " + library_id);
        //라이브러리 아이디로 이미지 검색
        Image findRepImg = jpaImageRepository.findByLibraryId(library_id).get();
        //라이브러리 아이디로 첨부파일 검색
        //List<File> findRepFiles = jpaFileRepository.findByLibraryId(library_id);
        for (File file : jpaFileRepository.findByLibraryId(library)) {
            System.out.println("file = " + file.getFile_name());
        }

        // webㅇㅔ 레포지토리 만들고
        // 이 프로젝트 경로로 와서 git init 하고 ,
        // git add . 모든 파일을 스테이지에 추가
        // git commit -m "djWjrn "
         // git branch -m Main
        // git push



        // 1. 라이브러리명
        // 2. 미리보기 이미지
        // 3. 첨부파일
        System.out.println("library_name = " + findRepLib.getLibrary_name());
        System.out.println("image = " + findRepImg.getImg_name());

    }
}
