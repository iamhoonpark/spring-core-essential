package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    // 회원 도메인 실행과 테스트
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        // MemberService memberService = new MemberServiceImpl();
        // AppConfig 에서 memberService 를 달라고하면, memberImpl 객체를 생성하면서 MemoryRepo 를 주입해줌
        MemberService memberService = appConfig.memberService();

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }

}
