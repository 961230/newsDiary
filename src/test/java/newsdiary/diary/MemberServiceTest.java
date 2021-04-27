package newsdiary.diary;

import newsdiary.diary.domain.Member;
import newsdiary.diary.repository.MemberRepository;
import newsdiary.diary.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    EntityManager em;

    @Test
    public void memberJoin() throws Exception{
        //given
        Member member = new Member();
        member.setName("hi");

        //when
        Long saveId = memberService.join(member);

        //then
        Assertions.assertEquals(member, memberRepository.findById(saveId));
    }
/*
    @Test
    public void 전체_회원_조회(){
        List<Member> members = memberService.findMembers();
        for(Member m : members){
            System.out.println("조회된 멤버 : "+m.getName());
        }
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("a");
        Member member2 = new Member();
        member2.setName("a");

        //when
        memberService.join(member1);
        try{
            memberService.join(member2);

        }catch(IllegalStateException e){
            return;
        }

        //then
        Assertions.fail("예외가 발생해야 한다.");
    }

 */
}
