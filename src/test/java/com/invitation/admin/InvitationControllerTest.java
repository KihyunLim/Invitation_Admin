package com.invitation.admin;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import javax.inject.Inject;

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
import com.invitation.biz.invitation.GalleryVO;
import com.invitation.biz.invitation.InvitationVO;
import com.invitation.biz.invitation.LoveStoryVO;
import com.invitation.biz.invitation.MainInfoVO;
import com.invitation.biz.invitation.SyntheticInvitationVO;
import com.invitation.biz.invitation.WhenWhereVO;
import com.invitation.controller.invitation.InvitationController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations= {"file:src/main/resources/applicationContext.xml", 
								"file:src/main/webapp/WEB-INF/config/servlet-config.xml"})
public class InvitationControllerTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceTest.class);
	
	private MockMvc mock;
	
	@Autowired
	InvitationController invitationController;
	
	@Before
	public void setup() {
		mock = MockMvcBuilders.standaloneSetup(invitationController).build();
	}
	
	@Test
//	@Ignore
	public void test_getMemberInfo() throws Exception {
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("id", "admin");
//		params.add("password", "1234");
		
		mock.perform(
				get("/invitation/getMemberInfo.do")
//				.params(params)
				.param("id", "test1"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(handler().handlerType(InvitationController.class))
		.andExpect(handler().methodName("getMemberInfo"));
	}
	
//	@Test
	@Ignore
	public void test_getInvitationList() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("id", "");
		params.add("name", "");
		params.add("beginDate", "");
		params.add("endDate", "");
//		params.add("page", "1");
		
		mock.perform(
				get("/invitation/getInvitationList.do")
				.params(params))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(handler().handlerType(InvitationController.class))
		.andExpect(handler().methodName("getInvitationList"));
	}
	
//	@Test
	@Ignore
	public void test_registerInvitaiton() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		SyntheticInvitationVO syntheticInvitationVO = new SyntheticInvitationVO();
		InvitationVO invitationVO = new InvitationVO();
		MainInfoVO mainInfoVO = new MainInfoVO();
		ArrayList<LoveStoryVO> loveStoryVO = new ArrayList<>();
		LoveStoryVO itemLoveStoryVO1 = new LoveStoryVO();
		LoveStoryVO itemLoveStoryVO2 = new LoveStoryVO();
		ArrayList<WhenWhereVO> whenWhereVO = new ArrayList<>();
		WhenWhereVO itemWhenWhereVO1 = new WhenWhereVO();
		WhenWhereVO itemWhenWhereVO2 = new WhenWhereVO();
		ArrayList<GalleryVO> galleryVO = new ArrayList<>();
		GalleryVO itemGalleryVO1 = new GalleryVO();
		GalleryVO itemGalleryVO2 = new GalleryVO();
		GalleryVO itemGalleryVO3 = new GalleryVO();
		
		invitationVO.setFormCode("hookup");
		invitationVO.setId("test2");
		invitationVO.setPeriodBegin("20200601");
		invitationVO.setPeriodEnd("20200630");
		invitationVO.setUseEachImage("Y");
		invitationVO.setUseG("Y");
		invitationVO.setUseLS("Y");
		invitationVO.setUsePyebaek("Y");
		invitationVO.setUseSM("Y");
		invitationVO.setVisible("Y");
		
		mainInfoVO.setAddress("경기도 성남시 분당구 벌말로39번길 13, qwer (야탑동)");
		mainInfoVO.setContentBride("신부 간단 소개");
		mainInfoVO.setContentGroom("신랑 간단 소개");
		mainInfoVO.setDateWedding("20200630");
		mainInfoVO.setFullNameBride("/2020/06/07/s_23f9687c-3457-443f-ab46-a910c048fea4_sample1.jpg");
		mainInfoVO.setFullNameGroom("/2020/06/07/s_9c352569-bc25-44ca-a96a-bd33ac23b692_sample2.jpg");
		mainInfoVO.setFullNameMain("/2020/06/07/s_8f8601f5-e5a6-4e56-9688-baf875298d18_sample1.jpg");
		mainInfoVO.setPlaceX("967868.67319358");
		mainInfoVO.setPlaceY("1934771.4323645737");
		mainInfoVO.setTimeWedding("1300");
		mainInfoVO.setUseEachImage("Y");
		
		itemLoveStoryVO1.setDateStory("20200608");
		itemLoveStoryVO1.setTitle("qq");
		itemLoveStoryVO1.setContent("ww");
		itemLoveStoryVO1.setFullNameImg("/2020/06/07/s_7a59626c-b8d5-4fc4-a6f4-1ccf7611504e_sample1.jpg");
		loveStoryVO.add(itemLoveStoryVO1);
		itemLoveStoryVO2.setDateStory("20200607");
		itemLoveStoryVO2.setTitle("ee");
		itemLoveStoryVO2.setContent("rr");
		itemLoveStoryVO2.setFullNameImg("/2020/06/07/s_eec24b48-7f20-454f-befd-c85b9ba6bb1f_sample2.jpg");
		loveStoryVO.add(itemLoveStoryVO2);
		
		itemWhenWhereVO1.setAddress("경기도 성남시 분당구 벌말로39번길 13, qwer (야탑동)");
		itemWhenWhereVO1.setContent("aa");
		itemWhenWhereVO1.setDateWedding("20200630");
		itemWhenWhereVO1.setFlagPyebaek("N");
		itemWhenWhereVO1.setPlaceX("967868.67319358");
		itemWhenWhereVO1.setPlaceY("1934771.4323645737");
		itemWhenWhereVO1.setTimeWedding("1300");
		itemWhenWhereVO1.setTitle("tt");
		whenWhereVO.add(itemWhenWhereVO1);
		itemWhenWhereVO2.setAddress("전라남도 해남군 해남읍 군청길 27, asdf");
		itemWhenWhereVO2.setContent("dd");
		itemWhenWhereVO2.setDateWedding("20200618");
		itemWhenWhereVO2.setFlagPyebaek("Y");
		itemWhenWhereVO2.setPlaceX("917327.0029600007");
		itemWhenWhereVO2.setPlaceY("1620377.7687504846");
		itemWhenWhereVO2.setTimeWedding("1715");
		itemWhenWhereVO2.setTitle("ss");
		whenWhereVO.add(itemWhenWhereVO2);
		
		itemGalleryVO1.setFullName("/2020/06/07/s_41d6a37f-84d1-4587-af73-c060b421528b_sample1.jpg");
		galleryVO.add(itemGalleryVO1);
		itemGalleryVO2.setFullName("/2020/06/07/s_245a86b0-dd5f-40b2-a34c-d154baa1c520_sample2.jpg");
		galleryVO.add(itemGalleryVO2);
		itemGalleryVO3.setFullName("/2020/06/07/s_27c97681-51ef-4c01-af12-95af6bc421bc_sample1.jpg");
		galleryVO.add(itemGalleryVO3);
		
		syntheticInvitationVO.setInvitationVO(invitationVO);
		syntheticInvitationVO.setMainInfoVO(mainInfoVO);
		syntheticInvitationVO.setLoveStoryVO(loveStoryVO);
		syntheticInvitationVO.setWhenWhereVO(whenWhereVO);
		syntheticInvitationVO.setGalleryVO(galleryVO);
		
		mock.perform(
					post("/invitation/registerInvitation.do")
					.content(mapper.writeValueAsString(syntheticInvitationVO))
					.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(handler().handlerType(InvitationController.class))
				.andExpect(handler().methodName("registerInvitaiton"));
	}
	
//	@Test
	@Ignore
	public void test_getMemberInvitationInfo() throws Exception {
		mock.perform(
				get("/invitation/getMemberInvitation.do")
				.param("id", "test2"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(handler().handlerType(InvitationController.class))
		.andExpect(handler().methodName("getMemberInvitation"));
	}
	
//	@Test
	@Ignore
	public void test_getSyntheticInvitation() throws Exception {
		mock.perform(
				get("/invitation/getSyntheticInvitation.do")
				.param("invSeq", "1"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(handler().handlerType(InvitationController.class))
		.andExpect(handler().methodName("getSyntheticInvitation"));
	}
	
//	@Test
	@Ignore
	public void test_modifyInvitation() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		InvitationVO invitationVO = new InvitationVO();
		
		invitationVO.setFormCode("hookup");
		invitationVO.setPeriodBegin("20200611");
		invitationVO.setPeriodEnd("20200721");
		invitationVO.setVisible("Y");
		invitationVO.setSeq(1);
		
		mock.perform(
					post("/invitation/modifyInvitation.do")
					.content(mapper.writeValueAsString(invitationVO))
					.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(handler().handlerType(InvitationController.class))
				.andExpect(handler().methodName("modifyInvitation"));
	}
}