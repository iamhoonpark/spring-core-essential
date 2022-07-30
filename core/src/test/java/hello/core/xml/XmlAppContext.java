package hello.core.xml;

import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class XmlAppContext {

    // XML 방식의 스프링 컨텍스트 설정 방법 ( <=> resource/appConfig.xml)
    // 즉, xml 기반의 appConfig.xml 스프링 설정정보와, 자바 코드로 된 AppConfig.java 설정 정보를 비교해보면 거의 비슷하다.
    // 최근에는 xml 기반으로 설정정보를 하지 않음

    @Test
    void xmlAppContext() {
        // GenericXmlApplicationContext 가 "appConfig.xml" 을 읽어서 동작하게 함
        ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
        // 출력 결과 : Creating shared instance of singleton bean 'memberService'
        // 싱글톤 빈으로 등록 완료
    }

}
