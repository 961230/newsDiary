package myportfolio.portfolio.service;

import myportfolio.portfolio.domain.Member;
import myportfolio.portfolio.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


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
        member.setName("sayho");

        //when
        Long saveId = memberService.join(member);

        //then
        Assertions.assertEquals(member, memberRepository.findOne(saveId));
    }

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
        fail("예외가 발생해야 한다.");
    }
}
