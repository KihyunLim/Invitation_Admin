package com.invitation.admin;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invitation.biz.admin.user.UserAdminVO;
import com.invitation.controller.invitation.InvitationController;
import com.invitation.controller.login.LoginController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/test/resources/applicationContext.xml")
public class DataSourceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceTest.class);
	
	@Inject
	private DataSource ds;
	private MockMvc mock;
	
	@Autowired
	LoginController loginController;
	@Autowired
	InvitationController invitationController;
	
	@Before
	public void setup() {
		mock = MockMvcBuilders.standaloneSetup(loginController).build();
//		mock = MockMvcBuilders.standaloneSetup(invitationController).build();
	}
	
//	@Test
	@Ignore
	public void testConnection() throws Exception {
		try (Connection con = ds.getConnection()) {
			LOGGER.debug("con : " + con);
			LOGGER.debug("@@@@@@@@@@@@@@@@@@@@@@");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test
//	@Ignore
	public void test_doLogin() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		UserAdminVO userAdminVO = new UserAdminVO();
		userAdminVO.setId("admin");
		userAdminVO.setPassword("1234");
		
		mock.perform(
				post("/login/login.do")
				.content(mapper.writeValueAsString(userAdminVO))
				.contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(handler().handlerType(LoginController.class))
		.andExpect(handler().methodName("doLogin"));
	}
	
//	@Test
	@Ignore
	public void test_getMemberInfo() throws Exception {
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("id", "admin");
//		params.add("password", "1234");
		
		mock.perform(
				get("/invitation/getMemberInfo")
//				.params(params)
				.param("id", "test2"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(handler().handlerType(InvitationController.class))
		.andExpect(handler().methodName("getMemberInfo"));
	}
}
