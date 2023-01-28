package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        // 컨테이너 생성
        // 이번엔 AutoAppConfig를 참조해서 빈 생성과 의존성 '자동' 주입
        // 그냥 AppConfig는 수동 주입이었다.
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        // 잘 등록되었는지 확인
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
