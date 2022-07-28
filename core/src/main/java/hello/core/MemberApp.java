package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    // 회원 도메인 실행과 테스트
    public static void main(String[] args) {
        // Spring 은 ApplicationContext 에서 시작 = 스프링 컨텍스트 = @Bean 들을 다 관리해줌
        // 파라미터에 담은 내용들을 Spring 컨텍스트에 객체를 등록하여 관리 시작
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        // 기존 찾아온 방법: AppConfig appConfig = new AppConfig();
        // 스프링 전환 후 객체를 찾아오는 방법(key, type)
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        // 실행 시, Creating shared instance of singleton bean 'appConfig.~' 스프링에 등록된 것

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }

}
