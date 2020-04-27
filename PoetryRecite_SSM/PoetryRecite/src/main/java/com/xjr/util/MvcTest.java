package com.xjr.util;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:mybatis-spring.xml","file:src/main/resources/spring-mvc.xml"} )
public class MvcTest {
	WebApplicationContext context;
	MockMvc mockMvc;
	@Before
	public void initMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testPage() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/userControllerr").param("pn", "1")).andReturn();
		MockHttpServletRequest request= result.getRequest();
		PageInfo pi = (PageInfo) request.getAttribute("pageInfo"); 
		System.out.println(pi.getPageNum());
		System.out.println(pi.getTotal());
		int [] nums = pi.getNavigatepageNums();
		for(int i:nums) {
		System.out.println(i);
		}
	}

}
