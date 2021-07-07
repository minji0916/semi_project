package semi.searchTestspring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class File {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long file_id;

    @ManyToOne //파일 여러개에 라이브러리 하나
    @JoinColumn(name = "library_id")
    private Library library_id;

    private String file_name;

    @Enumerated
    private FileStatusFlag fileStatusFlag;

    @Enumerated(EnumType.STRING)
    private FileTypeFlag fileTypeFlag;

}

//enum FileStatusFlag {
//    //0:최신,검색가능 1:수정됨 2:삭제됨
//    RECENT, MODIFIED, DELETED;
//}
//
//enum FileTypeFlag {
//    //1:엑셀 내부 이미지. 2:필수첨부파일 3:일반첨부파일
//    INTERNAL_IMAGE, REQUIRED_FILE, GENERAL_FILE;
//}
