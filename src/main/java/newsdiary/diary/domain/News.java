package newsdiary.diary.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class News {
    @Id
    @GeneratedValue
    @Column(name = "news_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // news 제목
    private String title;

    // news 내용
    @Lob
    @Type(type = "text")
    private String content;

    private LocalDateTime writeDate;
    /*
    @Lob
    @Type(type = "text")
    private String contact;

    private String duty;

    @Lob
    @Type(type = "text")
    private String dutydetail;
    */

    public static News createNews(Member member){
        News news = new News();
        news.setMember(member);
        news.setWriteDate(LocalDateTime.now());
        return news;
    }
}
