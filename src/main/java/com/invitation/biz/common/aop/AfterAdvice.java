package com.invitation.biz.common.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterAdvice {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AfterAdvice.class);
	
	@After("PointcutCommon.allPointcut()")
	public void finallyLog() {
		LOGGER.info("[After] 비즈니스 로직 수행 끝");
		LOGGER.info("-------------------------------------------------------------------------------------------------------");
	}
}
