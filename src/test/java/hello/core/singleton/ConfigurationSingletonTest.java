package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest() {
        ApplicationContext ac =  new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl mems = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl ores = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memrepo = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository m1 = mems.getMemberRepository();
        MemberRepository m2 = ores.getMemberRepository();
        System.out.println("m1 = " + m1);
        System.out.println("m2 = " + m2);
        System.out.println("memrepo = " + memrepo);

        Assertions.assertThat(mems.getMemberRepository()).isSameAs(memrepo);
        Assertions.assertThat(ores.getMemberRepository()).isSameAs(memrepo);
    }
    
    @Test
    void configuration(){
        ApplicationContext ac =  new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);
        System.out.println("bean.getClass() = " + bean.getClass());
        //hello.core.AppConfig$$EnhancerBySpringCGLIB$$4ff8daf5


    }
}
