package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {

        /*MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();*/

        /* Java Code
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService(); */

        // 위의 Java 코드를 Spring 으로 전환
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);
        // 실행 시 Creating shared instance of singleton bean 'appConfig' 스프링으로 객체(빈) 등록

        // 신규멤버
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);

        // 현재 DB가 없으니 메모리 객체에 저장
        memberService.join(member);

        // 주문
        Order order = orderService.createOrder(memberId, "Phone Case", 20000);

        // 호출
        System.out.println("order = " + order);
        System.out.println("order.calculatePrice = " + order.calculatePrice());
    }

}
