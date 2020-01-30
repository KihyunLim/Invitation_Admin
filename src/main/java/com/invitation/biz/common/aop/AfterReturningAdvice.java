package com.invitation.biz.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterReturningAdvice {

	private static final Logger LOGGER = LoggerFactory.getLogger(AfterReturningAdvice.class);
	
	@AfterReturning(pointcut="PointcutCommon.getPointcut()", returning="returnObj")
	public void afterLog(JoinPoint jp, Object returnObj) {
		String method = jp.getSignature().getName();
		
		if(returnObj != null) {
			LOGGER.info("[AfterReturning] " + method + " 메소드 리턴값 : " + returnObj.toString());
		} else {
			LOGGER.info("[AfterReturning] " + method + " 메소드 리턴값 : null");
		}
	}
}
