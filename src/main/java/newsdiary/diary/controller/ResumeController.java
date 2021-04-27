package newsdiary.diary.controller;

import lombok.RequiredArgsConstructor;
import newsdiary.diary.domain.Member;
import newsdiary.diary.domain.Resume;
import newsdiary.diary.repository.ResumeSearch;
import newsdiary.diary.service.MemberService;
import newsdiary.diary.service.ResumeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ResumeController {
    private final MemberService memberService;
    private final ResumeService resumeService;

    @GetMapping("/resume/new")
    public String createForm(Model model){
        List<Member> members = memberService.findMembers();

        model.addAttribute("resumeForm", new ResumeForm());
        model.addAttribute("members", members);
        return "/resume/createResumeForm";
    }

    @PostMapping("/resume/new")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("dutyType") String duty,
                        ResumeForm resumeForm){

        resumeService.write(memberId, resumeForm.getIntroduction(), resumeForm.getContact(), duty, resumeForm.getDutydetail());
        return "redirect:/";
    }

    @GetMapping("/resume")
    public String resumeList(Model model, @ModelAttribute("resumeSearch") ResumeSearch resumeSearch){
        List<Resume> resumeList = resumeService.searchResume(resumeSearch);
        model.addAttribute("resume", resumeList);
        return "resume/resumeList";
    }

    @GetMapping("resume/{resumeId}")
    public String resumeView(Model model, @PathVariable("resumeId") Long resumeId){
        Resume resume = resumeService.findResume(resumeId);
        model.addAttribute(resume);
        return "resume/resumeView";
    }

    @GetMapping("resume/{resumeId}/delete")
    public String deleteResume(@PathVariable("resumeId") Long resumeId){
        System.out.println("<<<<<<<<<<<<<<<<<<<이력서 삭제 Controller 진입 : "+resumeId);
        resumeService.deleteResume(resumeId);
        return "redirect:/";
    }
}
