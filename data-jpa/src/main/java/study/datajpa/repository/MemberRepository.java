package study.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.datajpa.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> { //인터페이스 보고 스프링데이터jpa가 만들어서 인젝션 해준 것!

    List<Member> findByUsername(String username); //공통으로 되지 않은 것 구현하는 것에 문제가 있음
    //필요한 것만 만들고 싶은데 이게 인터페이스여서 다 구현해줘야함.
    //근데 사실 스프링데이터jpa가 마법을 부려서 다 구현해줌. ->쿼리메서드 기능임
}
