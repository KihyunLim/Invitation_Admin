package com.invitation.admin;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.invitation.controller.member.MemberController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations= {"file:src/main/resources/applicationContext.xml", 
								"file:src/main/webapp/WEB-INF/config/servlet-config.xml"})
public class MemberControllerTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(MemberControllerTest.class);
	
	private MockMvc mock;
	
	@Autowired
	MemberController memberController;
	
	@Before
	public void setup() {
		mock = MockMvcBuilders.standaloneSetup(memberController).build();
	}
	
	@Test
//	@Ignore
	public void test_getMemberList() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("page", "1");
		params.add("searchCondition", "");
		params.add("searchKeyword", "");
		
		
		mock.perform(
				get("/member/getMemberList.do")
				.params(params))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(handler().handlerType(MemberController.class))
		.andExpect(handler().methodName("getMemberList"));
	}
}
