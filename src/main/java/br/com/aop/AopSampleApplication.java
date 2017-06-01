package br.com.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * https://docs.spring.io/spring/docs/current/spring-framework-reference/html/aop.html
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class AopSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopSampleApplication.class, args);
    }
}
