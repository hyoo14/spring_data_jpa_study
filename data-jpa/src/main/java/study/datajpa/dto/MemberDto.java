package study.datajpa.dto;

import lombok.Data;
import study.datajpa.entity.Member;

@Data //단순 dto니까 data써줌. 게터세터 다 들어간 것이므로 엔티티에는 써주면 안 됨.
public class MemberDto {
    private Long id;
    private String username;
    private String teamName;

    public MemberDto(Long id, String username, String teamName) {
        this.id = id;
        this.username = username;
        this.teamName = teamName;
    }

    public MemberDto(Member member){
        this.id = member.getId();
        this.username = member.getUsername();
    }
}
