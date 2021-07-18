package semi.minji0718.repository;

import semi.minji0718.domain.MyMember;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    MyMember saveMember(MyMember member);
    Optional<MyMember> findByNo(Long no);
    Optional<MyMember> findById(String id);
    /** 이름은 중복 가능 */
    List<MyMember> findByName(String name);
}
