package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();

        /*MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();*/

        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        // 신규멤버
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        // 현재 DB가 없으니 메모리 객체에 저장
        memberService.join(member);
        // 주문
        Order order = orderService.createOrder(memberId, "Phone Case", 10000);
        // 호출
        System.out.println("order = " + order);
        System.out.println("order.calculatePrice = " + order.calculatePrice());
    }

}
