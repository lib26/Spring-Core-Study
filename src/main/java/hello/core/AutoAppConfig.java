package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.springframework.context.annotation.ComponentScan.*;

// @ComponentScan은 @Component가 붙은 모든 클래스를 스프링 빈으로 등록한다. 당연히 싱글톤
// 컴포넌트 스캔에서 제외할 대상을 지정한다.
// 다른 test에서 사용한 컴포넌트들 코드 살리기 위함
// 보통 이렇게 Configuration을 제외하는 경우는 없다.
@Configuration
@ComponentScan(excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {
    // 기존 AppConfig와 다르게 수동으로 @Bean 등록을 해준 것이 아무것도 없다.

    // 근데 만약 수동으로 빈을 설정하는데, 컴포넌트 스캔으로 자동 빈 등록되어있는 빈이름과 중복되는 이름으로 한다면?
    // 수동으로 반영이된다. 즉 수동으로 해준 빈으로 등록이 오버라이딩된다.
    // Test는 AutoAppConfig에서 돌려보면 알 수 있다,
    // 하지만 또 스프링 부트는 자동 빈 등록 VS 수동 빈 등록 에 대해서 중복되는 빈이름이 있다면 오류를 발생시킨다.
    // 이 오류를 끄고싶다면 application.properties에서 spring.main.allow-bean-definition-overriding=true 를 해주면 된다.

    /*
    @Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
     */
}
