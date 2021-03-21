package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class ApplicationContextExtendFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Configuration
    static class TestConfig {
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
    }


    @Test
    @DisplayName("부 모타입으로 조회시, 자식이 둘이상있으면 중복오류가 발생한다 ")
    void findBeanByParentTypeDuplicate(){;
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () ->
                ac.getBean(DiscountPolicy.class));


//        String[] beans = ac.getBeanDefinitionNames();
//        for(String beanDefinitionName:beans){
//            Object beanDe = ac.getBean(beanDefinitionName);
//            System.out.println("name = " + beanDefinitionName); //soutm
//            System.out.println("object = " + beanDe); //soutm
//        }
    }


    @Test
    @DisplayName("부 모타입으로 조회시, 자식이 둘이상있으면 빈이름을 지정하면 된다 ")
    void findBeanByParentTypeBeanName(){
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(DiscountPolicy.class);


//        String[] beans = ac.getBeanDefinitionNames();
//        for(String beanDefinitionName:beans){
//            Object beanDe = ac.getBean(beanDefinitionName);
//            System.out.println("name = " + beanDefinitionName); //soutm
//            System.out.println("object = " + beanDe); //soutm
//        }
    }


    @Test
    @DisplayName("특정 하위 타입으로 조회  ")
    void findBeanBySubType() {
        RateDiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", RateDiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);


    }

    @Test
    @DisplayName("부모 타입으로 모두 조회  ")
    void findBeanByParentType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);


    }


    @Test
    @DisplayName("부모 타입으로 모두 조회  ")
    void findBeanByObjectType() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for(String key:beansOfType.keySet()){
            System.out.println("key = " + key + " value " + beansOfType.get(key));
        }


    }
}
