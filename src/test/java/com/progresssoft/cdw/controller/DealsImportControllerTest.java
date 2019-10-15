package com.progresssoft.cdw.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DealsImportControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testUpload() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/upload")).andExpect(status().isOk())
				.andExpect(view().name("upload-page"));
		resultActions.andReturn();
	}

	@Test
	public void testInquire() throws Exception {
		ResultActions resultActions = mockMvc.perform(get("/search")).andExpect(status().isOk())
				.andExpect(view().name("upload-summary"));
		resultActions.andReturn();
	}

}
