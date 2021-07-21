package semi.minji0718.domain;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "file_path")
@Data
public class MyFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="file_no")
    private Long no;

//    @ManyToOne //파일 여러개에 라이브러리 하나
//    @JoinColumn(name = "library_no")
//    private MyLibrary library;
    private Long library_no;

    @Column(name="upload_file_name")
    private String uploadFileName; //고객이 업로드한 파일명

    @Column(name="store_file_name")
    private String storeFileName;  //시스템에 저장하는 파일명 (UUID로 서버 내부에서 파일 명 안겹치

    @Column(name="file_path")
    private String path;

//    @Enumerated(EnumType.STRING)
//    private FileStatusFlag status_flag;
//
//    @Enumerated(EnumType.STRING)
//    private FileTypeFlag file_type;
}
