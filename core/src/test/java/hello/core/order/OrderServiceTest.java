package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService; // = new MemberServiceImpl();
    OrderService orderService; // = new OrderServiceImpl();

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {

        // Primitive type인 long 으로 선언해도 되나, 프리미티브 타입은 null을 넣을 수 없음
        // 현재는 객체 생성 단계에서 null이 허용될 수 있어서 Long type
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // 검증. VIP경우 1000원 할인 검증
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}
