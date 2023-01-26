package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

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
}
