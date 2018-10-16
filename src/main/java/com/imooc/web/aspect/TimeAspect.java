package com.imooc.web.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.junit.validator.PublicClassValidator;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeAspect {

	@Around("execution(* com.imooc.web.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("time aspect start");
		long start = new Date().getTime();
		Object[] args = joinPoint.getArgs();
		for (Object arg : args) {
			System.out.println("arg is " + arg);
			
		}
		Object object = joinPoint.proceed();
		System.out.println("time aspect 耗时" + (new Date().getTime() - start));
		System.out.println("time aspect end");
		return object;
	}
	
	
	
}
