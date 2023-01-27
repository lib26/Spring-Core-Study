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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 객체 생성과 주입
 * Config에서는 '역할'과 '구현'이 한눈에 드러나게, 보이게끔 하는 것이 중요하다.
 * Bean에 등록이 될 때 예를 들어
 * memberService를 key로 value는 MemberServiceImpl로 저장한다.
 * Bean으로 등록된 모든 메서드는 호출된다.
 */
@Configuration
public class AppConfig {

    // 회원 Service 역할
    @Bean
    public MemberService memberService() {
        // 구현체
        return new MemberServiceImpl(memberRepository());
    }

    // Repository 역할
    @Bean
    public MemberRepository memberRepository() {
        // 구현체
        return new MemoryMemberRepository();
    }

    // 주문 Service 역할
    @Bean
    public OrderService orderService() {
        // 구현체
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // 할인 정책 역할
    @Bean
    public DiscountPolicy discountPolicy(){
        // 구현체
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
