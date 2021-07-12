package com.example.minji0708.service;

import com.example.minji0708.domain.MyFile;
import com.example.minji0708.domain.MyLibrary;
import com.example.minji0708.repository.FileRepository;
import org.apache.tomcat.jni.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Transactional // @Transactional 애노테이션 : "데이터 변경"이 필요하므로 적어주기.
public class FileService {

    EntityManager em;

    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) { // 의존성 주입
        this.fileRepository = fileRepository;
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

    //파일 압축 풀어서 저장
    public void unZipFile(String zipPath, String zipFile, String unZipPath, String unZipFile){
        UnZip unZip = new UnZip();
        System.out.println("--------- 압축 해제 ---------");
        if (!unZip.unZip(zipPath, zipFile, unZipPath, unZipFile)) {
            System.out.println("압축 해제 실패");
         }
    }
}