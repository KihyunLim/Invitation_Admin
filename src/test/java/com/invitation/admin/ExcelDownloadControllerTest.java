package com.invitation.admin;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.invitation.controller.common.excelDownload.ExcelDownloadController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations= {"file:src/main/resources/applicationContext.xml",
		"file:src/main/webapp/WEB-INF/config/servlet-config.xml"})
public class ExcelDownloadControllerTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceTest.class);
	
	private MockMvc mock;
	
	@Autowired
	ExcelDownloadController excelDownloadController;
	
	@Before
	public void setup() {
		mock = MockMvcBuilders.standaloneSetup(excelDownloadController).build();
	}
	
	@Test
//	@Ignore
	public void test_excelDownloadSweetMessageList() throws Exception {
		mock.perform(
				get("/common/excelDownload.do")
				.param("invSeq", "5"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(handler().handlerType(ExcelDownloadController.class))
		.andExpect(handler().methodName("excelDownloadSweetMessageList"));
	}
}
