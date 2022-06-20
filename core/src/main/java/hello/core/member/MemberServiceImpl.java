package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // nullPointerException 방지를 위해 구현체를 선택
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
