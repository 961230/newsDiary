package newsdiary.diary.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import newsdiary.diary.domain.QResume;
import newsdiary.diary.domain.Resume;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ResumeRepository {

    private final EntityManager em;
    QResume r = QResume.resume;

    public void save(Resume resume){
        em.persist(resume);
    }

    public Resume findOne(Long id){
        Resume resume = em.find(Resume.class, id);
        return resume;
    }

    public List<Resume> findByName(ResumeSearch resumeSearch){
        JPAQueryFactory query = new JPAQueryFactory(em);
        List<Resume> list =
                query.selectFrom(r)
                .where(eqName(resumeSearch.getName()))
                .fetch();

        return list;
    }

    public void deleteOne(Long id){
        System.out.println("이력서 id : "+id);
        Resume resume = em.find(Resume.class, id);
        System.out.println("resume 정보 : " + resume.toString());
        em.remove(resume);
        em.flush();
        em.find(Resume.class, id);
        System.out.println("resume remove 이후 : " + resume.toString());
    }

    private BooleanExpression eqName(String name){
        if(StringUtils.isBlank(name))
            return null;
        return r.member.name.like("%"+name+"%");
    }
}
