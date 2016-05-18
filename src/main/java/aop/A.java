package aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class A {

    @Pointcut("execution(* *.getVersion(..))")
    public void sleeppoint(){}
    
    @Before("sleeppoint()")
    public void beforeSleep(){
        System.out.println("before ---");
    }
    
    @AfterReturning("sleeppoint()")
    public void afterSleep(){
        System.out.println("after ----");
    }
	
}
