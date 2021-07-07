package semi.searchTestspring.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Library {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="library_id") // 실제 db 컬럼 명
    private Long id; // 코딩 할때는 어차피 id 니까.

    private String member_name; // 작성자
    private String library_name; // 라이브러리명
    private int views; // 조회수

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name="regist_date")
    private LocalDateTime requestDateTime; //등록일

    //날짜가 url로 들어오면, Library requestDateTime이 String 타입으로 들어오는 date를 LocalDateTime로 변환하지 못하면서 에러가 납니다.
    //Format도 지정해주고 이러한 문제도 없애기 위해 @DateTimeFormat을 사용합니다.
    //url="/requestParameter?requestDateTime=2018-12-15T10:00:00" (get방식, controller, LocalDateTime 변수명)
    //https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=varkiry05&logNo=221736856257
    //https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=writer0713&logNo=221596636122
    //https://jojoldu.tistory.com/361

}
