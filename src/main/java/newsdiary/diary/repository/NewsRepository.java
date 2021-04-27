package newsdiary.diary.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import newsdiary.diary.domain.News;
import newsdiary.diary.domain.QNews;
import newsdiary.diary.domain.QResume;
import newsdiary.diary.domain.Resume;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class NewsRepository {
    private final EntityManager em;
    QNews qn = QNews.news;

    public void save(News news){
        em.persist(news);
    }

    public News findById(Long id){
        News news = em.find(News.class, id);
        return news;
    }

    public List<News> findByName(ResumeSearch resumeSearch){
        JPAQueryFactory query = new JPAQueryFactory(em);
        List<News> newslist =
                query.selectFrom(qn)
                        .where(eqName(resumeSearch.getName()))
                        .fetch();

        return newslist;
    }

    public void deleteById(Long id){
        System.out.println("news id : "+id);
        News news = em.find(News.class, id);
        System.out.println("News 정보 : " + news.toString());
        em.remove(news);
        em.flush();
        em.find(News.class, id);
        System.out.println("news remove 이후 : " + news.toString());
    }

    private BooleanExpression eqName(String name){
        if(StringUtils.isBlank(name))
            return null;
        return qn.member.name.like("%"+name+"%");
    }
}
