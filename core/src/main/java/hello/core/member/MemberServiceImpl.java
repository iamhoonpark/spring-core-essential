package hello.core.member;


public class MemberServiceImpl implements MemberService {

    // interface만 가지고 있으면 nullponterexception발생, nullPointerException 방지를 위해 구현체를 직접 선택

    /*  그러나 문제가 발생
        MemberServiceImple은 MemberRepository를 의존하고 있음
        그러나 오른쪽 실제 할당하는 부분이 구현제를 의존하고 있음
        그래서 MemberServiceImple은 MemberRepository와 MemoryMemberRepository를 의존 중
        추상화에도 의존하고 객체에도 의존하고 있기 때문에 추 후 변경이 있을 때, 문제가 된다.
        한마디로 DIP를 위반하고 있는 것 */
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 각 메서드는 다형성에 의해서 MemberRepository interface가 아니라 MemoryMemberRepository에 있는 구현체 override 메서드들이 호출됨

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
