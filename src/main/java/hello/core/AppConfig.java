package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// 생성과 주입
// '역할'과 '구현'이 한눈에 드러나게, 보이게끔 하는 것이 중요하다
public class AppConfig {

    // 회원 Service 역할
    public MemberService memberService() {
        // 구현체
        return new MemberServiceImpl(memberRepository());
    }

    // Repository 역할
    private MemberRepository memberRepository() {
        // 구현체
        return new MemoryMemberRepository();
    }

    // 주문 Service 역할
    public OrderService orderService() {
        // 구현체
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // 할인 정책 역할
    public DiscountPolicy discountPolicy(){
        // 구현체
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
