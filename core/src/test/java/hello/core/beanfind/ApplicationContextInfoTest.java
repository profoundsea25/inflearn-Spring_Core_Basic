package hello.core.beanfind;


import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    // 전체 Bean 출력해보기
    @Test
    @DisplayName("모든 빈 출력하기")
    // @test는 public 생략 가능
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        // 리스트같은 객체가 있을 때 iter을 쓰면 반복문을 자동으로 만들어준다.
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            // soutv로 변수 찍기
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
        }
    }

    // 코드 드래그하고 Ctrl+D 하면 복사
    // 우리가 등록한 bean만 보기
    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        // 리스트같은 객체가 있을 때 iter을 쓰면 반복문을 자동으로 만들어준다.
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
            // ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                // soutv로 변수 찍기
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }
        }
    }
}
