package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

// 새로운 할인 정책 (기존 고정 할인에서 등급별 차등 할인)

public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {

        // 회원 등급이 VIP일 경우 10% 활인 정책
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }

        /*
        * Test 단축키 : Ctrl + Shift + T
        * > Testing Library: JUnit5 > OK
        * */

    }
}
