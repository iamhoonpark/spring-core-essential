package hello.core.order;

public interface OrderService {
    // 클라이언트가 주문생성(createOrder)할 때, 회원id, 상품명, 상품가격을 파라미터로 넘겨야 한다.
    // 그러면, 해당 내용에 대한 최종 주문결과(createOrder메서드)를 return으로 반환한다.
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
