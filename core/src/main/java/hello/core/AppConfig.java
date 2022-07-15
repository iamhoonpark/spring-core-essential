package hello.core;

/*
* 애플리케이션의 전체 동작 방식을 구성(config)하기 위해, 구현 객체를 샌성하고 연결하는 책임을 가지느 별도의 설정 클래스
* - 관심사 분리 목적
*  · 구성에 관한 것은 여기서 다 한다.
*  · 배우는 본인의 역할인 배역을 수행하는 것에만 집중
*  · 남자배우 디카프리오는 어떤 여자 주인공이 선택되더라도 똑같이 공연을 할 수 있어야 함
*  · 공연을 구성하고, 담당 배우를 섭외하고, 역할에 맞는 배우를 지정하는 책임을 담당하는 별도의 공연 기획자(=AppConfig)
*  · 공연 기획자를 만들고, 배우와 공연 기획자의 책임을 확실히 분리
*/

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Spring 애플리케이션이 어떻게 구성되는지 설정하는 것(= 설정 정보, 구성 정보)
public class AppConfig {

    /*
        이 전에는 객체를 생성하고, 해당 객체에 어떤 interface 가 들어가야 하는지, 할당하는 지 이런 내용을 해당 객체의 Impl 에서 직접 할당 했었다.
        ex) MemberServiceImpl 클래스 내부 : private final MemberRepository memberRepository = new MemoryMemberRepository();
        - 이 형태는 배우가 직접 담당 배우들을 섭외하는 격
        - 따라서 앞으로 그런 내용들은 AppConfig 로 옮겨준다.

        MemberServiceImpl 을 반환하는 메서드를 선언
        - 어디선가 MemberService 를 선언해서 사용할 경우
         · MemberService 의 구현체가 생성이 되고,
         · MemoryMemberRepository 가 여기에 들어감
        - 생성자 주입
         · 생성자를 통해서 객체가 (new instance)해서 생성된게 들어감
    */
    @Bean // Spring Bean 을 선언할 경우 스프링컨테이너에 등록이 됨
    public MemberService memberService() {
        return new MemberServiceImpl(memoryMemberRepository());
    }

    // AppConfig Refactoring : interface 를 반환해주는 역할 Ctrl + Alt + M
    @Bean
    public MemoryMemberRepository memoryMemberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        // OrderService 는 사용하는 field 가 2개
        return new OrderServiceImpl(memoryMemberRepository(), discountPolicy());
    }

    // AppConfig Refactoring
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
/*
    AppConfig 정리
    - AppConfig 의 등장으로 애플리케이션이 크게 ①사용영역 ②객체를 생성하고 구성(Configuration)하는 영역으로 분리
    - AppConfig 를 통해서 관심사를 확실하게 분리했다.
    - AppConfig 는 공연 기획자 즉, AppConfig 는 구체 클래스를 선택하고, 배역에 맞는 담당 배우를 캐스팅한다.
      즉, 애플리케이션이 어떻게 동작해야 할지 전체 구성을 책임진다.
    - 이제 각 클래스(배우들)은 담당 기능(역할)을 실행하는 책임만 지면 된다.
      ex) OrderServiceImpl 은 기능을 실행하는 책임만 지면 된다.

    AppConfig Refactoring 장점
    - 코드만 봐도 어떤 역할인지 직관적
    - 변동사항이 발생 시 해당 코드만 수정하면 됨
    - 중복 제거
     · ex) new MemoryMemberRepository() 등
    - 역할을 세우고 구현이 들어가게 하는 코딩
*/