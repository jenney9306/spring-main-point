package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;


class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService s1 = ac.getBean(StatefulService.class);
        StatefulService s2 = ac.getBean(StatefulService.class);

        //threadA : A 사용자가 10000원 주문
        int aprice = s1.order("A", 10000);

        //threadB : B 사용자가 20000원 주문
        int bprice = s2.order("B", 20000);
        
        //A가 사용자 주문금액 조회
        int price = aprice;
        System.out.println("price = " + price);
        

    }

    static class TestConfig {
        @Bean
        public  StatefulService statefulService() {
            return new StatefulService();
        }
    }

}