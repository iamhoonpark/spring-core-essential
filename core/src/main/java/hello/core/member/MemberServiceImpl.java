package hello.core.member;


public class MemberServiceImpl implements MemberService {

    // interface 만 가지고 있으면 nullponterexception 발생, nullPointerException 방지를 위해 구현체를 직접 선택

    /*  그러나 문제가 발생
        MemberServiceImpl 은 MemberRepository 를 의존하고 있음
        그러나 오른쪽 실제 할당하는 부분이 구현제를 의존하고 있음
        그래서 MemberServiceImpl 은 MemberRepository 와 MemoryMemberRepository 를 의존 중
        추상화에도 의존하고 객체에도 의존하고 있기 때문에 추 후 변경이 있을 때, 문제가 된다.
        한마디로 DIP 를 위반하고 있는 것
        - 추상화에만 의존하게 설정
        - MemberServiceImpl 은 구체적으로 잘 모르고
        - 외부에서(AppConifg) 생성해서 넣어줌
        - 그래서 이제 ServiceImpl 은 MemoryMemberRepository 에 대한 코드가 전혀 사라짐 */
    private final MemberRepository memberRepository; // = new MemoryMemberRepository();

    public MemberServiceImpl(MemberRepository memberRepository) {
        // 인자를 통해 MemoryMemberRepository 가 들어와서
        // memberRepository 에 MemoryMemberRepository 가 할당된 다.
        this.memberRepository = memberRepository;
    }

    // 각 메서드는 다형성에 의해서 MemberRepository interface 가 아니라 MemoryMemberRepository 에 있는 구현체 override 메서드들이 호출됨

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
