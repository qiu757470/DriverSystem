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

	Logger log = Logger.getLogger(LoggerAOP.class);//��־������־�Ķ���

	@Before("execution(* org.great.mapper.*.*(..))")
	public void before(Method arg0, Object[] arg1, Object arg2) throws Throwable {
		log.info("---ǰ��֪ͨ---");
		log.info("Ŀ��"+arg2+"����"+arg0.getName()+"����"+Arrays.toString(arg1));
	}
	@AfterReturning("execution(* org.great.mapper.*.*(..))")//���Ҫ��mapper???
	public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
		log.info("---����֪ͨ---");
		log.info("����"+arg3+"��"+arg1.getName()+"����"+arg0+arg2);
	}
	
	@AfterThrowing(value="execution(public int com.yl.spring.aop.ArithmeticCalculator.*(..))", throwing="ex")
	public void afterThrowing(JoinPoint joinPoint, Exception ex) {
		log.info("---�쳣֪ͨ---");
		String methodName = joinPoint.getSignature().getName();
		System.out.println("�����쳣�ķ�����" + methodName + " �쳣: " + ex);
	}
}
