package br.com.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
    void logBeforeAdvice(final JoinPoint joinPoint) {
        log.info(joinPoint.toString());
        log.info("Calling controller method...");
    }

    @After("logPointcut() && args(raiseError)")
    void logAfterAdvice(final boolean raiseError) {
        log.info("Returning from controller method...");
    }

    @AfterReturning(pointcut = "logPointcut()", returning = "result")
    void logAfterReturningAdvice(final Object result) {
        log.info("Returned value: {}", result);
    }

    @AfterThrowing(pointcut = "logPointcut()", throwing = "ex")
    void logAfterThrowingAdvice(final Exception ex) {
        log.error(ex.getMessage(), ex);
    }

    @Around("logPointcut()")
    Object logAroundAdvice(final ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Before calling method");
        final Object result = joinPoint.proceed();
        log.info("After calling method");
        return result;
    }
}
