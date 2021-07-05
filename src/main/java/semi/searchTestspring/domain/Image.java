package semi.searchTestspring.domain;

import javax.persistence.*;

@Entity
@Table(name = "IMG_PATH")
public class Image {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long img_file_no;

    @OneToOne
    @JoinColumn(name = "library_no")
    private Long library_no;
    private String img_file_name;

    public Long getImg_file_no() {
        return img_file_no;
    }

    public void setImg_file_no(Long img_file_no) {
        this.img_file_no = img_file_no;
    }

    public Long getLibrary_no() {
        return library_no;
    }

    public void setLibrary_no(Long library_no) {
        this.library_no = library_no;
    }

    public String getImg_file_name() {
        return img_file_name;
    }

    public void setImg_file_name(String img_file_name) {
        this.img_file_name = img_file_name;
    }
}
