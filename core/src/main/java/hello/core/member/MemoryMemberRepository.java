package hello.core.member;

import java.util.HashMap;
import java.util.Map;

// 회원 저장소

/* interface와 구현체는 서로 다른 패키지에 위치하는 것이 설계상 바람직하나 예제가 복잡하기 같은 패키지에 설계
*  현재 DB가 확정되지 않은 상황이기에 메모리 리포지토리만 생성해두어 개발을 진행할 수 있게 됨
*  또한 서버를 재실행할 경우 DB에 저장되는 개념이 아니라 테스트 용도로 적절 */
public class MemoryMemberRepository implements MemberRepository {

    // 저장소이기에 Map 필요
    // 실무 기준 : 여러군데에서 접근하기 때문에 이슈방지를 위해 ConcoruntHashMap을 사용
    // 동시성 이슈가 발생할 수 있지만 개발용도로 예제이기 때문에 단순하게 HashMap
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
