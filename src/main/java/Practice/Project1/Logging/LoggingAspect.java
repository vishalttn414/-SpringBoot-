package Practice.Project1.Logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {


    @Around("execution(* Practice.Project1..*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long timeTaken = System.currentTimeMillis() - start;
        String methodPath = joinPoint.getSignature().getDeclaringTypeName()
                + "." + joinPoint.getSignature().getName();
        System.out.println("total Time taken by  "+ methodPath+" is: " + timeTaken);

        return result;
    }
}
