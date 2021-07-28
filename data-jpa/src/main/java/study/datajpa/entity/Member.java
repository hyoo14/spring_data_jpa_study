package study.datajpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue//pk값 jpa가 알아서 순차적으로 넣어줌.
    private Long id;
    private String username;

    protected Member() { //jpa 프록싱 기술 쓸 때, 객체를 강제로 만들기 때문에 막으려면 프로텍티드로해야함.
    }

    public Member(String username) {
        this.username = username;
    }

}
