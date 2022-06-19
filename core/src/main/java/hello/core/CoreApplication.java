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
	Tips. 그레이들 통해서 실행할 때, 오래걸리기 때문에 인텔리제이가 자바를 바로 실행하기에 속도 향상
	File > Settings > Build Tools > Gradle
	> Build and runs using > IntelliJ IDEA
	> Run tests using > IntelliJ IDEA
*/