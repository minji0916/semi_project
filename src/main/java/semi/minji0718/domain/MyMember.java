package semi.minji0718.domain;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter @Setter
public class MyMember {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_no")
    private Long no;

    @Column(name="member_id")
    private String id;

    @Column(name = "member_level")
    private String level;

//    @NonNull
    @Column(name = "member_name")
    private String name;

}
