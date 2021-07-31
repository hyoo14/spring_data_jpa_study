package study.datajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;
import java.util.UUID;

@EnableJpaAuditing //이거 넣어줘야 데이터jpa에서 auditing 됩니다.
@SpringBootApplication
//@EnableJpaRepositories(basePackages = "study.datajpa.repository") //원래 잡아줘야하지만 스프링부트를 쓰므로 부트가 다 해줌
public class DataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataJpaApplication.class, args);
	}

	@Bean
	public AuditorAware<String > auditorProvider(){
		//실제로는 스프링시큐리터에서 세션 정보 등 가져와서
		return () -> Optional.of(UUID.randomUUID().toString());
	}

}
