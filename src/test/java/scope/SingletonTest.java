package scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    public void singletonBeanFind() {
        // 프로토타입 빈과 다르게 싱클톤 빈은 컨테이너가 만들어지고 빈을 바로 등록하는 것 같다
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);
        assertThat(singletonBean1).isSameAs(singletonBean2);
        ac.close(); //종료
    }

    @Scope("singleton") // 디폴트라 안해도 되긴하다
    // @Component 위에서 컨테이너 생성할 때 파라미터로 넣어주기 때문에 생략 가능. 즉, 자동으로 componentScan 대상으로 포함된다.
    static class SingletonBean {
        @PostConstruct // 빈 생성, 의존성 주입 이후 호출되는 초기화 콜백 메소드
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy // 컨테이너가 끝날 때 호출되는 소멸 전 콜백 메소드
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }
}