package semi.minji0718.service;

import org.junit.jupiter.api.Assertions;
import semi.minji0718.domain.MyMember;
import semi.minji0718.repository.JpaMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    JpaMemberRepository jpaMemberRepository;

    @Test
    @Rollback(value = false) // 테스트가 끝나도 롤백하고 싶지 않음
    public void member_store() throws Exception {
        //Given
        // member1 생성
        MyMember member1 = new MyMember();
        member1.setId("id_1");
        member1.setLevel("level_1");
        member1.setName("jia");

        // member2 생성
        MyMember member2 = new MyMember();
        member2.setId("id_2");
        member2.setLevel("level_2");
        member2.setName("minji");

        //When : memeber1, memeber2 저장
        MyMember savedMember1 = jpaMemberRepository.saveMember(member1);
        MyMember savedMember2 = jpaMemberRepository.saveMember(member2);

        System.out.println("------------------------------------------------");
        System.out.println("member1 = " + savedMember1);
        System.out.println("member1_no = " + savedMember1.getNo());
        System.out.println("member1_id = " + savedMember1.getId());
        System.out.println("member1_level = " + savedMember1.getLevel());
        System.out.println("member1_name = " + savedMember1.getName());

        System.out.println("------------------------------------------------");
        System.out.println("member2 = " + savedMember2);
        System.out.println("member2_no = " + savedMember2.getNo());
        System.out.println("member2_id = " + savedMember2.getId());
        System.out.println("member2_level = " + savedMember2.getLevel());
        System.out.println("member2_name = " + savedMember2.getName());
    }

    @Test
    @Rollback(value = false)
    public void member_findAll() throws Exception{
        //Given
        // member1 생성
        MyMember member1 = new MyMember();
        member1.setId("id_1");
        member1.setLevel("level_1");
        member1.setName("jia");

        // member2 생성
        MyMember member2 = new MyMember();
        member2.setId("id_2");
        member2.setLevel("level_2");
        member2.setName("minji");

        //When : memeber1, memeber2 저장
        MyMember savedMember1 = jpaMemberRepository.saveMember(member1);
        MyMember savedMember2 = jpaMemberRepository.saveMember(member2);

        //Then: member 다 검색
//        List<MyMember> findMemAll = jpaMemberRepository.findAll();
//        for ( MyMember m : findMemAll) {
//            System.out.println("member = " + m.getName());
//        }

    }

    @Test
    @Rollback(value = false)
    public void member_id_search() throws Exception{
        //Given
        // member1 생성
        MyMember member1 = new MyMember();
        member1.setId("id_1");
        member1.setLevel("level_1");
        member1.setName("jia");

        //When : memeber1
        MyMember savedMember1 = jpaMemberRepository.saveMember(member1);

        // member_id로 service에서 member 검색
        Optional<MyMember> findMemById = memberService.findMemberId("id_1");

        //Then: member_id로 repository에서 member 검색
        MyMember findMem = jpaMemberRepository.findById(savedMember1.getId()).get();

        // 이름 비교
        Assertions.assertEquals(findMemById.get().getName(), findMem.getName());
    }

    @Test
    @Rollback(value = false)
    public void member_id_name_search() throws Exception{
        //Given
        // member1 생성
        MyMember member1 = new MyMember();
        member1.setId("id_1");
        member1.setLevel("level_1");
        member1.setName("jia");

        //When : memeber1
        MyMember savedMember1 = jpaMemberRepository.saveMember(member1);

        //Then : id 들어오면 이름 내놔  " id >> member >> name "
        MyMember findMem = jpaMemberRepository.findById(savedMember1.getId()).get();
        System.out.println(findMem.getName());
    }

    @Test
    @Rollback(value = false)
    public void member_many_names() throws Exception {
        //Given
        // member1 생성
        MyMember member1 = new MyMember();
        member1.setId("id_1");
        member1.setLevel("level_1");
        member1.setName("jia");

        // member2 생성
        MyMember member2 = new MyMember();
        member2.setId("id_2");
        member2.setLevel("level_1");
        member2.setName("minji");

        // member3 생성
        MyMember member3 = new MyMember();
        member3.setId("id_3");
        member3.setLevel("level_2");
        member3.setName("minji");

        //When : memeber1
        MyMember savedMember1 = jpaMemberRepository.saveMember(member1);
        MyMember savedMember2 = jpaMemberRepository.saveMember(member2);
        MyMember savedMember3 = jpaMemberRepository.saveMember(member3);

        //Then
        // 이름 한명일 때
        System.out.println("------------------------------------------------");
        List<MyMember> findByName = memberService.findMemberName("jia");
        System.out.println("------------------------------------------------");
        for ( MyMember m : findByName) {
            System.out.println("member_id = " + m.getId());
            System.out.println("member_name = " + m.getName());
        }

        // 이름 여러명일 때
        List<MyMember> findByNames = memberService.findMemberName("minji");
        System.out.println("------------------------------------------------");
        for ( MyMember m : findByNames) {
            System.out.println("member_id = " + m.getId());
            System.out.println("member_name = " + m.getName());
        }
    }
}