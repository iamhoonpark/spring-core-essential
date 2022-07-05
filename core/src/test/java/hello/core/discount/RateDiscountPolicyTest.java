package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    // 10프로 할인이 되는지 확인

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다") // JUnit5 부터 제공하는 기능 DisplayName
    void vip_ok() {
        //given : 임의의 멤버를 하나 만들고
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        //when : 멤버를 넘기고, 가격을 넘겨줌
        int discount = discountPolicy.discount(member, 10000);

        //then
        Assertions.assertThat(discount).isEqualTo(1000);
        // 참고로 Assertions는 static에 import하는게 좋다. Alt + Enter > on-dement static import
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다")
    void vip_fail() {
        Member member = new Member(1L, "memberBAGIC", Grade.BASIC);
        int discount = discountPolicy.discount(member, 10000);
        Assertions.assertThat(discount).isEqualTo(1000);
        /* 실행결과 [Expected:1000, Acual:0] 기대했던 것은 1000원인데 실제 값은 0이다 */
    }

}