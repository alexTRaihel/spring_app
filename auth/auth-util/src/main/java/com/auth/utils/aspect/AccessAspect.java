package com.auth.utils.aspect;

import com.auth.utils.annotation.AccessRole;
import com.auth.utils.exeption.AccessDeniedException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class AccessAspect {

    @Before("@annotation(com.auth.utils.annotation.AccessRole)")
    public void checkRoles(JoinPoint jp) {
        AccessRole[] securedAnnotations = ((MethodSignature) jp.getSignature()).getMethod().getAnnotationsByType(AccessRole.class);

        for (AccessRole securedRole : securedAnnotations) {
            if (!hasAccess(securedRole, "ROLE_ADMIN")) {
                throw new AccessDeniedException("Access denied");
            }
        }
    }

    private boolean hasAccess(AccessRole securedRole, String sourceWithRoles){
        return securedRole.role().equalsIgnoreCase(sourceWithRoles);
    }
}
