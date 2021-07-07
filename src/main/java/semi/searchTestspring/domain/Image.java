package semi.searchTestspring.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Image {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long img_id;

    @OneToOne
    @JoinColumn(name = "library_id")
    private Library library_id;
    /** 조인할 때, library_id의 "참조타입"이 참조할 엔티티타입이어야 한다. */

    private String img_name;
}
