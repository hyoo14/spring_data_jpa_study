package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom, JpaSpecificationExecutor<Member> { //인터페이스 보고 스프링데이터jpa가 만들어서 인젝션 해준 것! //인터페이스 만든 걸 상속해줌!

    //    List<Member> findByUsername(String username); //공통으로 되지 않은 것 구현하는 것에 문제가 있음
//    //필요한 것만 만들고 싶은데 이게 인터페이스여서 다 구현해줘야함.
//    //근데 사실 스프링데이터jpa가 마법을 부려서 다 구현해줌. ->쿼리메서드 기능임
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findTop3HelloBy();

//    @Query(name = "Member.findByUsername") //주석처리해도 잘 동작함 //근데 네임드쿼리 실무에서 별로 사용 안 함// 쿼리 스트링 틀린 거 컴파일 단에서 찾아주는 장점은 있음.
    List<Member> findByUsername(@Param("username") String username); //명확히 jpql 있을 때 @Param 해줌 -> named parameter 넘어가야하므로

    @Query("select m from Member m where m.username = :username and m.age = :age") //쿼리스트링 오타쳤을 때 실행 시 잡아줌! 이것이 큰 장점! //근데 쿼리dsl이 가장 깔끔하고 유지보수 좋음(동적도 가능)
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Member m")
    List<String > findUsernameList();

    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t") //dto할 때 new 로 묶어줘야함
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") List<String> names);

    List<Member> findListByUsername(String username); //컬렉션
    Member findMemberByUsername(String username); //단건
    Optional<Member> findOptionalByUsername(String username); //단건 Optional

    //@Query(value = "select m from Member m" ) //left join m.team t", countQuery = "select count(m.username) from Member m") //카운트 쿼리 최적화 차원에서 써줌
    //실무에서 소팅 안 되면 그냥 쿼리 이렇게 넣어줘야함 ㅇㅇ
    Page<Member> findByAge(int age, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberFetchJoin();

    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<Member> findMemberEntityGraph();

    @EntityGraph(attributePaths = {"team"})
//    @EntityGraph("Member.all")
    List<Member> findEntityGraphByUsername(@Param("username") String username);

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true") )
    Member findReadOnlyByUsername(String username);

    @Lock(LockModeType.PESSIMISTIC_WRITE) //jpa가 지원하는 기능. 스프링데이터jpa에서 편하도록 어노테이션 제공
    List<Member> findLockByUsername(String username);

    <T> List<T>  findProjectionsByUsername(@Param("username") String username, Class<T> type);

    @Query(value = "select username from member where username = ?", nativeQuery = true) //entity에 맞게 하려면 필드 다 적어줘야하고 한계가 많음.
    Member findByNativeQuery(String username); //네이티브 썼다는 것은 다 쪼인해서 하고 싶을 때임. 문제는 반환타입이 몇개 지원 안 됨.

    @Query(value = "select m.member_id as id, m.username, t.name as teamName " +
            "from member m left join team t",
            countQuery = "select count(*) from member",
            nativeQuery = true)
    Page<MemberProjection> findByNativeProjection(Pageable pageable);
}
