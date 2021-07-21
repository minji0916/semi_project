package semi.minji0718.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import semi.minji0718.domain.MyFile;
import semi.minji0718.domain.MyLibrary;
import semi.minji0718.domain.UploadFile;
import semi.minji0718.repository.FileRepository;

import javax.persistence.EntityManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional // @Transactional 애노테이션 : "데이터 변경"이 필요하므로 적어주기.
public class FileService {

    EntityManager em;

    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) { // 의존성 주입
        this.fileRepository = fileRepository;
    }

    @Value("${file.dir}")
    private String fileDir;

    // 파일 path 가져옴
    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    //file_no로 검색
    public Optional<MyFile> findFileNo(Long no) {
        return fileRepository.findByFileNo(no);
    }

    //library로 file 검색
    public List<MyFile> findLibraryFile(MyLibrary library) {
        Long library_no = library.getNo();
        return fileRepository.findByLibraryNo(library_no);
    }

    // 업로드 파일이 여러 개일 때, multipartFile이 비어있지 않으면 storreFileResult에 추가
    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            //multipartFile이 비어있지 않으면 storeFileResult에 추가
            if(!multipartFile.isEmpty()){
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    // 파일 저장
    // multipartFile 받아서 파일 저장
    // UploadFile 형태로 반환 : uploadFileName, storeFileName( UUID.확장자 )
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException
    {
        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();      //원래 파일 이름
        String storeFileName = createStoreFileName(originalFilename);       //서버에 저장하는 파일명.확장자
        multipartFile.transferTo(new File(getFullPath(storeFileName)));     //storeFileName으로 FullPath 만들어서 파일 저장

        return new UploadFile(originalFilename, storeFileName);
    }

    // 서버에 저장하는 파일명 만드는 함수
    public String createStoreFileName(String originalFilename) {
        String ext = extractExt(originalFilename);      // 확장자 추출
        String uuid = UUID.randomUUID().toString();     // 랜덤한 UUID
        return uuid + "." + ext;                        // 서버에 저장하는 파일명 = UUID.확장자
    }

    // 확장자 추출하는 함수
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");        // "."의 위치 가져옴
        return originalFilename.substring(pos + 1);             // "." 다음에 있는 string 가져옴 = 확장자
    }

}