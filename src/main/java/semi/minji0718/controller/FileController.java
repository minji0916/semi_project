package semi.minji0718.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;
import semi.minji0718.domain.MyFile;
import semi.minji0718.domain.UploadFile;
import semi.minji0718.repository.FileRepository;
import semi.minji0718.repository.JpaFileRepository;
import semi.minji0718.service.FileService;
import semi.minji0718.service.UnZip;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileStore;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@Transactional
public class FileController {
    @Value("${file.dir}")
    private String fileDir;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    FileService fileService;

    //임시 라이브러리 no
    private long library_no_seq = 0L;


    @GetMapping("/files/new")
    public String newFile(@ModelAttribute FileForm form) {
        return "upload-form";
    }

    @GetMapping("/upload-form")
    public String reLoad(@ModelAttribute FileForm form) {
        return "upload-form";
    }

    @PostMapping("/files/new")
    public String saveFile(@ModelAttribute FileForm form, RedirectAttributes redirectAttributes, HttpServletResponse response) throws IOException {
        MultipartFile multipartFile = form.getAttachFile();
        String originalFilename = multipartFile.getOriginalFilename();


        // 1. zip 파일 저장
        UploadFile attachFile = fileService.storeFile(multipartFile);

        // 2. 압축 해제 (라이브러리명 밑에 압축해제)
        // upZip(zipPath, zipFileName, zipUnzipPath, unZipFile)
        UnZip unZip = new UnZip();
        if (!unZip.unZip(fileDir, attachFile.getStoreFileName(), fileDir, form.getLibraryName())) {
            log.info("fail Unzip");
        }

        // 2-1. 압축 해제한 파일 접근
        String filePath = fileDir + form.getLibraryName();
        File dirFile = new File(filePath);
        File[] fileList = dirFile.listFiles();
        String tempFileName = "";

        //데이터베이스에 저장
        for (File tempFile : fileList) {
            tempFileName = tempFile.getName();
            String storeFileName = fileService.createStoreFileName(tempFileName);

            MyFile file = new MyFile();
            file.setUploadFileName(tempFileName);
            file.setStoreFileName(storeFileName);
            file.setPath(fileDir+form.getLibraryName());
            file.setLibrary_no(library_no_seq);

            fileRepository.saveFile(file);
        }

        // 저장 됐을 때, 아이템 아이디 넘겨줌
        redirectAttributes.addAttribute("libraryNo", library_no_seq);

        library_no_seq++;

        // 아이템 아이디 넘겨받음
        return "redirect:/files/{libraryNo}";
    }

    @GetMapping("/files/{no}")
    public String files(@PathVariable Long no, Model model) {
        log.info("heyhey here");

        // 아이템 찾기
        List<MyFile> files =  fileRepository.findByLibraryNo(no);
        log.info("files: " + files);

        for (int idx=0; idx<files.size(); idx++) {
            log.info("please give file_name : " + files.get(idx).getUploadFileName());
        }

        // 아이템 넘겨 받음
        model.addAttribute("files", files);

        return "file-view";
    }

    // 첨부파일 다운
    // @ResponseBody 이거 써도 되지만, header에 추가할 것 있어서 ResponseEntity<Resource> 이렇게 씀
    // no를 넘겨받는 이유 : 아이템에 접근할 수 있는 사용자만 다운로드 가능함
    @GetMapping("/attach/{fileNo}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long no) throws MalformedURLException {

        log.info("hihihi");
        MyFile file = fileRepository.findByFileNo(no).get();
        String storeFileName = file.getStoreFileName();   // 파일 찾아서 다운 받아야 하니까
        String uploadFileName = file.getUploadFileName();   // 실제 사용자가 다운로드 받을 때, 업로드한 파일명으로 보여주려고

        log.info("why!!!: " + uploadFileName);
        UrlResource resource = new UrlResource("file:" + file.getPath() + file.getUploadFileName());     // 파일경로에 접근해서 가져옴

        log.info("uploadFileName={}", uploadFileName);

        // 그냥 UploadFileName을 contentDisposition해도 되는데,
        // 한글이나 특수문자는 깨질 수 있으니까 encode를 utf-8로 해주고 인코딩해서 파일명 넘겨줌 (ex. 문서.png )
        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
        // header를 안넣으면 파일에 적혀있는 내용만 볼 수 있고, 다운이 안됨 (그냥 브라우저 열어서 파일 내용만 보게 됨)
    }


}
