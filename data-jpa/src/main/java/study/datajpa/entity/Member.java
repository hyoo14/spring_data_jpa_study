package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username","age"}) // 연관관계는 ToString 하지 말것
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null){
            changeTeam(team);
        }

    }

    //jpa가 프록시 할때 private면 생성시 문제가 생길 수 있어서 protected로 하는게 좋음
    //따로 생성자 선언시 꼭 선언해줘야함 또는 @NoArgsConstructor(access = AccessLevel.PROTECTED) 사용
//    protected Member(){
//    }

    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }
}
