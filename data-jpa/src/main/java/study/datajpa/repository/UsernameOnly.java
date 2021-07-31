package study.datajpa.repository;

import org.springframework.beans.factory.annotation.Value;

public interface UsernameOnly {

    @Value("#{target.username + ' ' + target.age}") //이런 걸 open projection 이라고 함 //그냥 다 가져오는 거.
    String getUsername();
}
