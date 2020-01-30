package com.invitation.biz.common.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutCommon {

	@Pointcut("execution(* com.invitation.biz..*Impl.*(..))")
	public void allPointcut() {}
	
	@Pointcut("execution(* com.invitation.biz..*Impl.get*(..))")
	public void getPointcut() {}
}
