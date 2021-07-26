package study.datajpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;


    public Member(String username) {
        this.username = username;
    }
    //jpa가 프록시 할때 private면 생성시 문제가 생길 수 있어서 protected로 하는게 좋음
    //따로 생성자 선언시 꼭 선언해줘야함
    protected Member(){
    }
}
