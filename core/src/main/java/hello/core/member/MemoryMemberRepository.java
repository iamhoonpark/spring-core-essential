package hello.core.member;

import java.util.HashMap;
import java.util.Map;

/*
 * 회원 저장소 ①메모리 회원 저장소, ②DB 회원 저장소, ③외부 시스템 연동 회원 저장소
 *
 * interface와 구현체는 서로 다른 패키지에 위치하는 것이 설계장 바람직
 * 그러나 예제를 간단히 하기 위해 같은 패키지에 설계
 *
 * 현재 DB가 확정되지 않은 시나리오
 * 따라서 메모리 리포지토리만 생성해두면 개발을 진행할 수 있음
 * 또한 서버를 재실행할 경우 DB에 저장되는 개념이 아니라서 테스트 용도로 적절
 */
public class MemoryMemberRepository implements MemberRepository {
    
    /* 저장소이기 때문에 Map 객체가 필요함
     * 실무 기준 : 여러군데에서 접근하기 때문에 이슈방지를 위해 ConcurrentHashMap 사용
     * 동시성 이슈가 발생하나, 개발용도 예제이기 때문에 단순하게 HashMap 사용 */
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
