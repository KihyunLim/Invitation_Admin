package com.invitation.biz.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class BeforeAdvice {

	private static final Logger LOGGER = LoggerFactory.getLogger(BeforeAdvice.class);
	
	@Before("PointcutCommon.allPointcut()")
	public void beforeLog(JoinPoint jp) {
		String method = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		
		LOGGER.info("-------------------------------------------------------------------------------------------------------");
		if(args.length > 0) {
			LOGGER.info("[Before] " + method + " 메소드 args 정보 : " + args[0].toString());
		} else {
			LOGGER.info("[Before] " + method + " 메소드");
		}
	}
}
