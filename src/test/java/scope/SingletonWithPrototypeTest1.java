package scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean {

        // ObjectProvider는 우리가 생성한 빈이 아닌 스프링이 자동으로 생성해준 빈
        // 생성자 주입으로 바꿔주는 것이 좋다. 테스트라서 일단
        // @Autowired
        // private ObjectProvider<PrototypeBean> prototypeBeanProvider;

        // ObjectProvider와는 다르게 스프링에 의존하지 않는 java 표준이다. ObjectProvider와 똑같이 동작한다
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            // getObject를 호출하면 이때 프로토타입 빈을 생성한다. 이전 처럼 컨테이너한테 직접 부탁하는 것이 아니다.
            // 즉, 딱 지정한 빈을 컨테이너에서 대신 찾아주는 기능(DL)만 제공하는 것
            // 핵심 컨셉은 프로토타입 빈을 찾아주는 것이 아닌 DL을 제공한다는 것
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
