package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    // 주문 생성 요청이 들어오면, 회원정보를 요청 > 할인정책 > 최종 할인된 가격을 받아서 > 최종 생성된 주문을 반환

    // MeberRepository 에서 회원을 찾아야 하기 때문에 선언
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /*
    * 1) OrderServiceImpl 에서 DiscountPolicy 인터페이스와 FixDiscountPolicy 인 구현체 클래스도 함께 의존하고 있다 = DIP 위반
    * 2) 고정할인(Fix) 정책에서 유동적 할인(Rate) 정책으로 변경
    *    - private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    *    그러나 발생하는 문제점: RateDiscountPolicy 로 바꾸는 순간 OrderServiceImpl 에 코드를 수정해야 함 이것이 의존성 = OCP 위반
    *    - private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    *    OCP 위반: 내가 기름차에서 전기차로 바꿔도 면허증만 있으면 운전이 가능한 것과 동일한 원리
    * 3) 구현체가 아닌 인터페이스에만 의존하도록 의존관계를 변경
    *    - OrderServiceImpl 을 DiscountPolicy 에만 의존하도록 코드를 변경
    *    - final 키워드는 제거 : 값이 무조건 할당되야만 해당 키워드를 선언 가능
    * */
    private DiscountPolicy discountPolicy;

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        
        // 단일체계의 원칙을 잘 지킨 것
        // 만약 할인과 주문이 같은 레벨이었다면 유지보수가 보다 비체계적
        
        // 회원을 찾고
        Member member = memberRepository.findById(memberId);
        // 할인에 대한 것은 DiscountPolicy 객체가 담당
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

}