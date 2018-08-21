package com.kalahgame.sixpits.controller;

import com.kalahgame.sixpits.SixpitsApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SixpitsApplication.class)
@ActiveProfiles("FTest")
public class GameControllerTest {

    private static final int CONTENT_ID_START_POSITION = 7;
    private static final int EXTRA_CHARACTERS = 3;

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    // NewGame(HttpServletRequest request)
    @Test
    public void shouldAddaNewGame() throws Exception {
        MvcResult result =  mvc.perform(post("/games")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(responseContent("create"))
                .andReturn();
    }

    // Move(String gameId, String pitId, HttpServletRequest request)
    @Test
    public void shouldMoveAndDistributeStonesOfaPit() throws Exception {

        MvcResult result =  mvc.perform(put("/games/".concat(mockGameBoardId()).concat("/pits/1"))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(responseContent("move"))
                .andReturn();
    }

    private String mockGameBoardId() throws Exception {
        MvcResult result = mvc.perform(post("/games")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andReturn();
        return IdMiner(result) ;
    }

    private String IdMiner(MvcResult mvcResult) throws UnsupportedEncodingException {
        String content = mvcResult.getResponse().getContentAsString();
        return content.substring(CONTENT_ID_START_POSITION,content.indexOf("uri")-EXTRA_CHARACTERS);
    }

    private static ResultMatcher responseContent(String type) {
        return new ResultMatcher() {
            public void match(MvcResult result) throws UnsupportedEncodingException {
                assertTrue(result.getResponse().getContentType().contains("application/json") );
                assertTrue(result.getResponse().getContentAsString().contains("id") );
                if(type.equals("create")) {
                    assertTrue(result.getResponse().getContentAsString().contains("uri") );
                }
                else
                {
                    assertTrue(result.getResponse().getContentAsString().contains("status"));
                    assertTrue(result.getResponse().getContentAsString().contains("url") );
                }
            }
        };
    }
}