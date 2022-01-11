package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // 쓰레드 A : 사용자A 10_000원 주문
        statefulService1.order("userA", 10_000);
        // 쓰레드 B : 사용자B 20_000원 주문
        statefulService2.order("userB", 20_000);

        // 쓰레드 A : 사용자A 주문 금액 조회
        int price = statefulService1.getPrice();

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20_000);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}