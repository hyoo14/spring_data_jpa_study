package study.datajpa.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import javax.persistence.criteria.*;
//쓰지마시오
public class MemberSpec {

    public static Specification<Member> teamName(final String teamName){
        return (Specification<Member>) (root, query, builder) -> {

            if(StringUtils.isEmpty(teamName)){
                return null;
            }

            Join<Member, Team> t = root.join("team", JoinType.INNER);//회원과 조인
            return builder.equal(t.get("name"), teamName);

        };

    }

    public static Specification<Member> username(final String username){
        return (Specification<Member>) (root, query, builder) ->
            builder.equal(root.get("username"), username);

    }
    //너무 복잡해서 직관적이지도 않고 쓰기 힘듬.
}
