package br.com.aop.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

import br.com.aop.model.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class UserAspect {

    @DeclareParents(value = "br.com.aop.service.*", defaultImpl = AuditorImpl.class)
    private static Auditor mixin;

    @AfterReturning(pointcut = "execution(br.com.aop.model.User createUser()) && this(auditor)", returning = "user", argNames = "auditor,user")
    void changeVersion(final Auditor auditor, final User user) {
        log.info("Auditing {}", user);
        auditor.setUserVersion(user);
    }

    public interface Auditor {
        void setUserVersion(final User user);
    }

    public static class AuditorImpl implements Auditor {

        @Override
        public void setUserVersion(final User user) {
            Long version = user.getVersion();
            if (version == null) {
                version = 1L;
            } else {
                version++;
            }
            user.setVersion(version);
        }
    }
}
