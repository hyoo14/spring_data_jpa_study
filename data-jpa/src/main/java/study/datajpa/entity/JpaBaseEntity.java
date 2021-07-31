package study.datajpa.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //진짜 jpa상속은 아님. //속성들 내려서 테이블에서도 생성
public class JpaBaseEntity {

    @Column(updatable = false)
    private LocalDateTime createdDate;
    private LocalDateTime updatedData;

    @PrePersist
    public void prePersist(){
        LocalDateTime now = LocalDateTime.now();
        createdDate = now; //겹칠 때만 this 쓰고. 중요해서 강조할 때 쓰고 그 외에는 생략. 아이디를 다 쓰기 때문
        updatedData = now;
    }

    @PreUpdate
    public void preUpdate(){
        updatedData = LocalDateTime.now();
    }
}
