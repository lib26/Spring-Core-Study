package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

//lombok, @RequiredArgsConstructor가 final 필드값을 대상으로 아래의 생성자 코드를 그대로 만들어준다.
// 근데 @MainDiscountPolicy 이거까지 적용이 되지는 않는 것 같다. 다른 설정이 또 필요하다고 한다.
@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /**
     * OCP, DIP가 config로 인해 철저하게 지켜졌다
     * 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부('AppConfig')에서 결정된다
     * 따라서 OrderServiceImpl는 이제부터 실행에만 집중하면 된다
     */
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        // 회원 정보 조회
        // 물론 등급만 넘겨도 되지만 미래 확장성을 위해 객체 자체를 가져온다
        Member member = memberRepository.findById(memberId);

        // 객체 지향의 단일 체계원칙을 잘 지킨 예시이다.
        // 할인에 대한 세부 내용은 여기서 다리쥐 않고 결과만 리턴 받기 때문이다.
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 주문 결과를 클라이언트에 반환
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
