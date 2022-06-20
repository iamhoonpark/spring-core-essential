package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
/*
	Tip 1. 그레이들 통해서 실행할 때, 오래걸리기 때문에 속도 향상을 위해 인텔리제이가 자바를 바로 실행할 수 있도록 설정
	File > Settings > Build Tools > Gradle
	> Build and runs using > IntelliJ IDEA
	> Run tests using > IntelliJ IDEA
	
	Tip 2. 단축키에 대해서 알고 싶을 때
	File > Settings > Key map > 검색
*/