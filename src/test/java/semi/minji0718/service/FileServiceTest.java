package semi.minji0718.service;

import semi.minji0718.domain.*;
import semi.minji0718.repository.JpaFileRepository;
import semi.minji0718.repository.JpaImageRepository;
import semi.minji0718.repository.JpaLibraryRepository;
import semi.minji0718.repository.JpaMemberRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Transactional
class FileServiceTest {
    Logger log = (Logger) LoggerFactory.getLogger(FileServiceTest.class);

    @Autowired
    FileService fileService;
    @Autowired
    JpaFileRepository jpaFileRepository;
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
    public void give_member2_library4_image4_file() throws Exception {
        MyFile file = new MyFile();
//            file.setLibrary(form.getLibraryName());
//            file.setLibraryType(form.getLibraryType());
        file.setUploadFileName("tempFileName");
        file.setStoreFileName("storeFileName");

        jpaFileRepository.saveFile(file);
    }
}
//
//    @Test
//    @Rollback(value = false)
//    public void give_member2_library4_image4_file() throws Exception{
//        //Given
//        // member1 생성
//        MyMember member1 = new MyMember();
//        member1.setId("id_1");
//        member1.setLevel("level_1");
//        member1.setName("jia");
//
//        // member2 생성
//        MyMember member2 = new MyMember();
//        member2.setId("id_2");
//        member2.setLevel("level_2");
//        member2.setName("minji");
//
//        // library1 생성
//        MyLibrary library1 = new MyLibrary();
//        library1.setMember(member1);
//        library1.setName("jia_library");
//        library1.setViews(10);
//        library1.setRequestDateTime(LocalDateTime.now());
//
//        // library2 생성
//        MyLibrary library2 = new MyLibrary();
//        library2.setMember(member1);
//        library2.setName("not_minji_library");
//        library2.setViews(20);
//        library2.setRequestDateTime(LocalDateTime.now());
//
//        // library3 생성
//        MyLibrary library3 = new MyLibrary();
//        library3.setMember(member2);
//        library3.setName("minji_library");
//        library3.setViews(60);
//        library3.setRequestDateTime(LocalDateTime.now());
//
//        // library4 생성
//        MyLibrary library4 = new MyLibrary();
//        library4.setMember(member2);
//        library4.setName("not_jia_library");
//        library4.setViews(80);
//        library4.setRequestDateTime(LocalDateTime.now());
//
//        // image1 생성
//        MyImage image1 = new MyImage();
//        image1.setLibrary(library1);
//        image1.setMember(library1.getMember());
//        image1.setName("1.jpg");
//        image1.setStatus_flag(ImageStatusFlag.RECENT);
//
//        // image2 생성
//        MyImage image2 = new MyImage();
//        image2.setLibrary(library2);
//        image2.setMember(library2.getMember());
//        image2.setName("2.jpg");
//        image2.setStatus_flag(ImageStatusFlag.MODIFIED);
//
//        // image3 생성
//        MyImage image3 = new MyImage();
//        image3.setLibrary(library3);
//        image3.setMember(library3.getMember());
//        image3.setName("3.jpg");
//        image3.setStatus_flag(ImageStatusFlag.DELETED);
//
//        // image4 생성
//        MyImage image4 = new MyImage();
//        image4.setLibrary(library4);
//        image4.setMember(library4.getMember());
//        image4.setName("4.jpg");
//        image4.setStatus_flag(ImageStatusFlag.RECENT);
//
//        // file : library1과 연관된 file들 생성
//        // INTERNAL_IMAGE, RECENT
//        MyFile internal_file1 = new MyFile();
//        internal_file1.setFile_type(FileTypeFlag.INTERNAL_IMAGE);
//        internal_file1.setLibrary(library1);
//        internal_file1.setName("internal_file1");
//        internal_file1.setStatus_flag(FileStatusFlag.RECENT);
//
//        // INTERNAL_IMAGE, MODIFIED
//        MyFile internal_file2 = new MyFile();
//        internal_file2.setFile_type(FileTypeFlag.INTERNAL_IMAGE);
//        internal_file2.setLibrary(library1);
//        internal_file2.setName("internal_file2");
//        internal_file2.setStatus_flag(FileStatusFlag.MODIFIED);
//
//        // REQUIRED_FILE, RECENT
//        MyFile required_file1 = new MyFile();
//        required_file1.setFile_type(FileTypeFlag.REQUIRED_FILE);
//        required_file1.setLibrary(library1);
//        required_file1.setName("required_file1");
//        required_file1.setStatus_flag(FileStatusFlag.RECENT);
//
//        // GENERAL_FILE, DELETED
//        MyFile general_file1 = new MyFile();
//        general_file1.setFile_type(FileTypeFlag.GENERAL_FILE);
//        general_file1.setLibrary(library1);
//        general_file1.setName("general_file1");
//        general_file1.setStatus_flag(FileStatusFlag.DELETED);
//
//        //When : memeber, library, image, file 저장
//        MyMember savedMember1 = jpaMemberRepository.saveMember(member1);
//        MyMember savedMember2 = jpaMemberRepository.saveMember(member2);
//        MyLibrary savedLibrary1 = jpaLibraryRepository.saveLibrary(library1);
//        MyLibrary savedLibrary2 = jpaLibraryRepository.saveLibrary(library2);
//        MyLibrary savedLibrary3 = jpaLibraryRepository.saveLibrary(library3);
//        MyLibrary savedLibrary4 = jpaLibraryRepository.saveLibrary(library4);
//        MyImage savedImage1 = jpaImageRepository.saveImage(image1);
//        MyImage savedImage2 = jpaImageRepository.saveImage(image2);
//        MyImage savedImage3 = jpaImageRepository.saveImage(image3);
//        MyImage savedImage4 = jpaImageRepository.saveImage(image4);
//        MyFile savedFile1 = jpaFileRepository.saveFile(internal_file1);
//        MyFile savedFile2 = jpaFileRepository.saveFile(internal_file2);
//        MyFile savedFile3 = jpaFileRepository.saveFile(required_file1);
//        MyFile savedFile4 = jpaFileRepository.saveFile(general_file1);
//
//        List<MyLibrary> findLibAll = libraryService.findLibraries();
//
//        // 검색 화면
//        for ( MyLibrary library : findLibAll) {
//            MyImage image = imageService.findLibraryImage(library).get();
//            log.info("라이브러리 No : {}", library.getNo());
//            log.info("라이브러리명  : " + library.getName());
//            log.info("작성자       : " + library.getMember().getName());
//            log.info("이미지       : " + image.getName());
//            log.info("조회수       : " + library.getViews());
//            log.info("등록일       : " + library.getRequestDateTime());
//
//            List<MyFile> findFiles = fileService.findLibraryFile(library);
//            for (MyFile file : findFiles){
//                if (file.getStatus_flag() == FileStatusFlag.RECENT) {
//                    System.out.println("\n파일명            : " + file.getName());
//                    System.out.println("파일 status_flag : " + file.getStatus_flag());
//                    System.out.println("파일 type        : " + file.getFile_type());
//                    System.out.println("------------------------------------------------");
//                }
//            }
//        }
//    }
//
//    @Test
//    public void unZip_file() throws Exception{
//        MyLibrary library = libraryService.searchByLibraryName("minji_library").get();
//
//        // 압축 파일 위치와 압축된 파일
//        String zipPath = "C:/";
//        String zipFile = "background.zip";
//
//        // 압축을 해제할 위치, 압축할 파일이름
//        String unZipPath = "D:/Desktop/bimfile/";
//        String unZipFile = library.getNo() + "";
//// mkdir -> upzip
//        //
//        fileService.unZipFile(zipPath, zipFile, unZipPath, unZipFile);
//
//    }
//}