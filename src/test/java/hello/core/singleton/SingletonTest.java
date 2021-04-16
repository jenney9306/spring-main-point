package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링없는 순수한 ")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1.조회 : 호출할때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2.조회 : 호출할때마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른것
        System.out.println("mem1 " + memberService1);
        System.out.println("mem1 " + memberService2);

        //멤버1 != 멤버2
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);


    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용  ")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();


        System.out.println("mem1 " + singletonService1);
        System.out.println("mem1 " + singletonService2);

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);


    }


    @Test
    @DisplayName("스프링 컨테이너와 싱글톤  ")
    void springContainer() {
        ApplicationContext appConfig = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = appConfig.getBean("memberService", MemberService.class);
        MemberService memberService2 = appConfig.getBean("memberService", MemberService.class);

        // 참조값이 다른것
        System.out.println("mem1 " + memberService1);
        System.out.println("mem1 " + memberService2);

        //멤버1 != 멤버2
        Assertions.assertThat(memberService1).isSameAs(memberService2);


    }

//
//    public static void main(String[] args){
//        SingletonService singletonService = new SingletonService();
//    }

}
