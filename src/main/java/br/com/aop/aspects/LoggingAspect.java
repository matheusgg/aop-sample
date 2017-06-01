package br.com.aop.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    /**
     * execution(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern)
     * throws-pattern?)
     *
     * https://www.eclipse.org/aspectj/doc/released/progguide/semantics-pointcuts.html
     * https://www.eclipse.org/aspectj/doc/released/progguide/language-joinPoints.html
     */
    @Pointcut("execution(* br.com.aop.controller.*.*(..))")
    void logPointcut() {
    }

    @Before("logPointcut()")
    void logBeforeAdvice() {
        log.info("Calling controller method...");
    }

    @After("logPointcut()")
    void logAfterAdvice() {
        log.info("Returning from controller method...");
    }

    @AfterReturning(pointcut = "logPointcut()", returning = "result")
    void logAfterReturningAdvice(final Object result) {
        log.info("Returned value: {}", result);
    }
}
