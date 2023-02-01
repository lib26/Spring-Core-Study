package scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.*;

public class PrototypeTest {
    @Test
    public void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        ac.close(); //종료
    }

    @Scope("prototype") // 컨테이너가 빈 생성, 의존성 주입, 초기화 콜백까지만 관리한다
    static class PrototypeBean {
        @PostConstruct // 초기화 콜백
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy // 소멸 전 콜백
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
