package myportfolio.portfolio.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String name;

    private String email;

    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL) // Member가 지워지면 Resume 또한 지워진다.
    private Resume resume;

    @Embedded
    private Address address;



    /*@OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();*/
}
