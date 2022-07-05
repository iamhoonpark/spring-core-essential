package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

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
