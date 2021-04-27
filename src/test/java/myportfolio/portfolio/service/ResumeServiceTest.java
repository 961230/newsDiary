package myportfolio.portfolio.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import myportfolio.portfolio.domain.QResume;
import myportfolio.portfolio.domain.Resume;
import myportfolio.portfolio.repository.ResumeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ResumeServiceTest {

    @Autowired
    ResumeService resumeService;

    @Autowired
    ResumeRepository resumeRepository;

    @Autowired
    EntityManager em;

    @Test
    public void 선택_조회(){
        JPAQueryFactory query = new JPAQueryFactory(em);
        QResume r = QResume.resume;

        List<Resume> list =
                query.selectFrom(r)
                        .where(r.member.name.eq("황세호"))
                        .fetch();
        for(Resume rl : list){
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<"+rl.getWriteDate());
        }
    }
}