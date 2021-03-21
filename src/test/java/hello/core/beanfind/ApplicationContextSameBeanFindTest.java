package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
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

class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(sameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은타입이 둘이상있으면, 중복오류가 발생한다 ")
    void findBeanByTypeDuplicate(){
        //NoUniqueBeanDefinitionException
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () ->
                ac.getBean(MemberRepository.class));

    }

    @Test
    @DisplayName("타입으로 조회시 같은타입이 둘이상있으면, 빈 이름을 지정하면 된다 ")
    void findBeanByName(){
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);

    }

    @Test
    @DisplayName("특정타입을 모두 조회하기 ")
    void findAllBeanByType(){
        Map<String, MemberRepository> memberRepository = ac.getBeansOfType(MemberRepository.class);
        for(String key:memberRepository.keySet()){
            System.out.println("key = " + key + " value " + memberRepository.get(key));
        }
        assertThat(memberRepository.size()).isEqualTo(2);
//        assertThat(memberRepository).isInstanceOf(MemberRepository.class);

    }


    @Configuration
    static class sameBeanConfig { //static 클래스안에 클래스쓴건 이안에서만 쓰겠다는 뜻
        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }
}
