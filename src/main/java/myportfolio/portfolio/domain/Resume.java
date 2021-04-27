package myportfolio.portfolio.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Resume {
    @Id @GeneratedValue
    @Column(name = "resume_id")
    private long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime writeDate;

    @Lob
    @Type(type = "text")
    private String introduction;

    @Lob
    @Type(type = "text")
    private String contact;

    private String duty;

    @Lob
    @Type(type = "text")
    private String dutydetail;

    public static Resume createResume(Member member){
        Resume resume = new Resume();
        resume.setMember(member);
        resume.setWriteDate(LocalDateTime.now());
        return resume;
    }

}
