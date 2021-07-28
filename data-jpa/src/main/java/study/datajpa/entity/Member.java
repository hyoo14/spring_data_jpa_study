package study.datajpa.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
public class Member {

    @Id @GeneratedValue//pk값 jpa가 알아서 순차적으로 넣어줌.
    @Column(name = "member_id") //실무에서 좋아하시는 방법. 테이블은 관례상 조인할 때 편하기도 하고.
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY) //기본은 eager이지만 lazy로 세팅해줘야함. 매니투원 연관관계!. 성능최적화를 위해서! //지연로딩-멤버 조회할 때는 딱 멤버만 조회
    //팀을 볼 때 그 때(팀이 필요할 때) 디비에서 조회를 해오는 것!
    @JoinColumn(name = "team_id") //foreign key 명
    private Team team;


//    protected Member() { //jpa 프록싱 기술 쓸 때, 객체를 강제로 만들기 때문에 막으려면 프로텍티드로해야함.
//    } // 이걸 지우고 @NoArgsConstructor(access = AccessLevel.PROTECTED) 해주면 됨

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if( team != null){
            changeTeam(team);
        }

        changeTeam(team);
    }

    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }

}
