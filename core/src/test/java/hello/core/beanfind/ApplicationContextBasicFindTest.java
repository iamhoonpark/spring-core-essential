package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass = " + memberService.getClass());
        // 검증은 Assertions
        // isInstanceOf : memberService 가 어떤 객체 인스턴스인가?
        // Assertions 에 마우스 포인터 놓고 Alt + Enter : static import
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        // 그러나 같은 Type 이 여러개 일 경우 여러개가 같이 조회됨
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회") // interface 명이 아닌 구현체 Impl = 반환타입
    void findBeanByName2() {
        // 구체타입으로 조회하면 유연성이 떨어짐.
        // 항상 역할과 구현을 분리해야 한다. 그리고 역할에 의존해야 한다.
        // 그러나 이것은 구현에 의존했기 때문에 좋은 코드는 아니다.
        // 그러나 비이상적으로 안 될 경우 경우의 수를 놓고 테스트를 하는 것이 좋기 때문에 사용성이 있음
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회되는 것이 없다.")
    void findBeanByNameX() {
        // MemberService xxxx = ac.getBean("xxxx", MemberService.class);
        // 출력결과 : NoSuchBeanDefinitionException

        // 해당 예외가 터질 경우 테스트가 통과
        // 그런데, 어떤 로직을 실행했을 때? = 람다식 사용
        // 즉, 아랫줄 람다식이 실행됐을 때, 왼쪽에 있는 예외가 터져야 함
       assertThrows(NoSuchBeanDefinitionException.class,
               () -> ac.getBean("xxxx", MemberService.class));
    }

}
