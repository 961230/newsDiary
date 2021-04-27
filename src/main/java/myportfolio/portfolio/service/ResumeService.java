package myportfolio.portfolio.service;

import lombok.RequiredArgsConstructor;
import myportfolio.portfolio.domain.Member;
import myportfolio.portfolio.domain.Resume;
import myportfolio.portfolio.repository.MemberRepository;
import myportfolio.portfolio.repository.ResumeRepository;
import myportfolio.portfolio.repository.ResumeSearch;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResumeService {
    private final ResumeRepository resumeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long write(Long memberId, String intro, String contact, String duty, String detail){
        validateDuplicateResume(memberId);
        Member member = memberRepository.findOne(memberId);
        Resume resume = Resume.createResume(member);
        resume.setIntroduction(intro);
        resume.setContact(contact);
        resume.setDuty(duty);
        resume.setDutydetail(detail);
        resumeRepository.save(resume);
        return resume.getId();
    }

    public void validateDuplicateResume(Long memberId){
        Member one = memberRepository.findOne(memberId);
        ResumeSearch resumeSearch = new ResumeSearch();
        resumeSearch.setName(one.getName());
        List<Resume> resume = resumeRepository.findByName(resumeSearch);
        if(!resume.isEmpty())
            throw new IllegalStateException("이미 작성한 이력서가 있습니다.");
    }

    public List<Resume> searchResume(ResumeSearch resumeSearch){

        return resumeRepository.findByName(resumeSearch);
    }

    public Resume findResume(Long resumeId){
        return resumeRepository.findOne(resumeId);
    }

    @Transactional
    public void deleteResume(Long resumeId){
        resumeRepository.deleteOne(resumeId);
    }
}
