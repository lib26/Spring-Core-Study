package hello.core;

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
}
