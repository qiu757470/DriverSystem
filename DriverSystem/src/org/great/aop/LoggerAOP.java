package org.great.aop;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

public class LoggerAOP implements BeforeAdvice, AfterReturningAdvice, ThrowsAdvice{

	Logger log = Logger.getLogger(LoggerAOP.class);//日志处理，日志的对象

	@Before("execution(* org.great.mapper.*.*(..))")
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		log.info("---前置通知---");
		log.info("目标"+arg2+"方法"+arg0.getName()+"参数"+Arrays.toString(arg1));
	}
	@AfterReturning("execution(* org.great.mapper.*.*(..))")//这段要给mapper???
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
		log.info("---后置通知---");
		log.info("调用"+arg3+"的"+arg1.getName()+"方法"+arg0+arg2);
	}
	
	@AfterThrowing(value="execution(public int com.yl.spring.aop.ArithmeticCalculator.*(..))", throwing="ex")
	public void afterThrowing(JoinPoint joinPoint, Exception ex) {
		log.info("---异常通知---");
		String methodName = joinPoint.getSignature().getName();
		System.out.println("出现异常的方法：" + methodName + " 异常: " + ex);
	}
}
