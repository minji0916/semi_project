package semi.searchTestspring.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BIM_CONTENTS")
public class Library {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long library_no;
    private String member_name;
    private String library_name;
    private int views;
    private Date regist_date;

    public Long getLibrary_no() {
        return library_no;
    }

    public void setLibrary_no(Long library_no) {
        this.library_no = library_no;
    }

    public String getMember_name() {
        return member_name;
    }

    public void setMember_name(String member_name) {
        this.member_name = member_name;
    }

    public String getLibrary_name() {
        return library_name;
    }

    public void setLibrary_name(String library_name) {
        this.library_name = library_name;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Date getRegist_date() {
        return regist_date;
    }

    public void setRegist_date(Date regist_date) {
        this.regist_date = regist_date;
    }
}
