package study.datajpa.repository;

import org.springframework.stereotype.Repository;
import study.datajpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository //컴포넌트 스캔 대상으로 정해줌 어노테이션 통해. 스프링 빈에 등록됨
public class MemberJpaRepository {

    @PersistenceContext
    private EntityManager em; //jpa쓰려면 em 있어야.

    public Member save(Member member){
        em.persist(member); //jpa가 알아서 db에 인서트쿼리 날려서 저장
        return member;
    }

    public Member find(Long id){
        return em.find(Member.class, id); //jpa가 알아서 db에 셀렉트쿼리 날려서 찾아줌
    }
}
