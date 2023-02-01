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
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);
        assertThat(singletonBean1).isSameAs(singletonBean2);
        ac.close(); //종료
    }

    @Scope("singleton") // 디폴트라 안해도 되긴하다
    static class SingletonBean {
        @PostConstruct // 빈 생성, 의존성 주입 이후 호출되는 초기활 콜백 메소드
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy // 컨테이너가 끝날 때 호출되는 소멸 전 콜백 메소드
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }
}