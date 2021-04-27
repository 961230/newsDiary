package myportfolio.portfolio.controller;

import myportfolio.portfolio.domain.Address;
import myportfolio.portfolio.domain.Member;
import myportfolio.portfolio.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    /**
     * @Valid 유효성 검사
     * ex) 이메일 주소는 @ 형식으로 요청이 들어와야 한다.
     * 이럴 경우 '@'가 없거나 다른 기호거나, 영문이 아닌 한글이면 이메일 주소가 될 수 없으므로 @Valid 어노테이션으로 체크해줄 수 있다.
     */
    @PostMapping("/members/new")
    public String create(@Valid MemberForm memberForm, BindingResult result){
        if(result.hasErrors()){
            return "members/createMemberForm";
        }
        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());

        Member member = new Member();
        member.setName(memberForm.getName());
        member.setAddress(address);
        member.setEmail(memberForm.getEmail());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
