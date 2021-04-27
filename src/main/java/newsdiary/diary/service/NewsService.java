package newsdiary.diary.service;

import lombok.RequiredArgsConstructor;
import newsdiary.diary.domain.Member;
import newsdiary.diary.domain.News;
import newsdiary.diary.domain.Resume;
import newsdiary.diary.repository.MemberRepository;
import newsdiary.diary.repository.NewsRepository;
import newsdiary.diary.repository.ResumeRepository;
import newsdiary.diary.repository.ResumeSearch;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NewsService {

    private final NewsRepository newsRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long post(Long memberId, String title, String content){

        Member member = memberRepository.findById(memberId);
        News news = News.createNews(member);
        news.setTitle(title);
        news.setContent(content);
        return news.getId();
    }
/*
    public void validateDuplicateResume(Long memberId){
        Member one = memberRepository.findById(memberId);
        ResumeSearch resumeSearch = new ResumeSearch();
        resumeSearch.setName(one.getName());
        List<News> news = newsRepository.findByName(resumeSearch);
        if(!resume.isEmpty())
            throw new IllegalStateException("이미 작성한 이력서가 있습니다.");
    }

    public List<News> searchNews(ResumeSearch resumeSearch){
        return resumeRepository.findByName(resumeSearch);
    }

    public News findNews(Long id){
        return newsRepository.findById(id);
    }

    @Transactional
    public void deleteResume(Long resumeId){
        resumeRepository.deleteOne(resumeId);
    }*/
}
