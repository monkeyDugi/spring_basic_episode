package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class); // 이 시점에 초기화 메서드 호출
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class); // 이 시점에 초기화 메서드 호출

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        ac.close(); // 종료 메서드 호출되지 않음.
        // 이렇게 직접 종료 해주어야 함. 즉, 스프링 컨테이너의 영역을 벗어난 것.
        prototypeBean1.close();
        prototypeBean2.close();
    }

    @Scope("prototype")
    static class PrototypeBean {

        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void close() {
            System.out.println("SingletonBean.close");
        }
    }
}
