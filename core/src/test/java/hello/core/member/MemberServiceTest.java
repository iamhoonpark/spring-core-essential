package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    // MemberService memberService = new MemberServiceImpl();
    MemberService memberService;
    @BeforeEach // BeforeEach 테스트를 실행하기 전 반드시 실행하게 하는 것
    public void beforeEach() {
        // @Test 를 실행하기 전에 appConfig 를 만들고
        AppConfig appConfig = new AppConfig();
        // memberService 를 할당
        memberService = appConfig.memberService();
    }

    // 이 단위 테스트는 순수 스프링 컨테이너 없이 JAVA 코드로 테스트, 검증되는 것

    @Test
    void join() {
        // given(~ 이런 환경이 주어졌을 때,)
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when(~ 이렇게 했을 때)
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then(~ 이렇게 된다)
        // 검증API(옵션: member와 findMember가 똑같냐?)
        Assertions.assertThat(member).isEqualTo(findMember);
        // 녹색불이 들어왔다면 이상무
    }

}
