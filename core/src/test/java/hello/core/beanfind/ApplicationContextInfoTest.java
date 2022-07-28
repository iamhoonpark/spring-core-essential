package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// JUnit5 부터는 public 설정 안 해도 됨
class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("스프링 컨테이너에 있는 모든 빈 출력하기") // + Spring 기반의 Bean 까지 모두 출력됨
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        // List, 배열이 있을 경우 iter 작성 후 Tab 또는 Enter 하면 for문 자동완성
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + " | object = " + bean);
        }
    }

    @Test
    @DisplayName("스프링 컨테이너에 있는 애플리케이션 빈만 출력하기")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        // List, 배열이 있을 경우 iter 작성 후 Tab 또는 Enter 하면 for문 자동완성
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);// Bean 하나 하나에 대한 메타 정보

            // 스프링 내부를 위해 등록한 빈이 아닌, 내가 등록한 개발하기 위해 등록한 빈 또는 외부 라이브러리만 출력하게 함
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + " | object = " + bean);
            }
            // BeanDefinition.ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
            // BeanDefinition.ROLE_INFRASTRUCTURE: 스프링 내부에서 사용하는 빈
        }
    }

}
