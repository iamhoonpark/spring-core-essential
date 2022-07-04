package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    // 주문 생성 요청이 오면, 회원 정보를 요청 > 할인정책 > 최종 할인된 가격을 받아서 > 최종 생성된 주문을 반환

    // MemberRepository에서 회원을 찾아야 하기 때문에 필요
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // 고정 할인 정책
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

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