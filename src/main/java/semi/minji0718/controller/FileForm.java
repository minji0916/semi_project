package semi.minji0718.controller;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

// 파일 저장용 폼 (사용자 폴더에 직접 저장)
@Data
public class FileForm {
    private Long fileNo;
    private String libraryName;
    private String libraryType;
    private MultipartFile attachFile;
}