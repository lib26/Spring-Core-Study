package hello.core.member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    /**
     * OCP, DIP가 config로 인해 철저하게 지켜졌다
     * 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부('AppConfig')에서 결정된다
     * 따라서 MemberServiceImpl는 이제부터 실행에만 집중하면 된다
     */
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
