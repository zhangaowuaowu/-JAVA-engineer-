package com.cathay.java.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * coindesk單元測試
 * @param
 * @return
 * @author 刁卓
 * Change History:
 * Last Modify author :刁卓 Date:  Version:1.0
 * change Description:
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CoindeskControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * 呼叫coindesk api，測試内容是否如期待。
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    @Test
    public void getCoindeskTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/coindesk")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.time.updated").value("Sep 2, 2024 07:07:20 UTC"))
                .andExpect(jsonPath("$.disclaimer").value("just for test"))
                .andExpect(jsonPath("$.chartName").value("Bitcoin"))
                .andExpect(jsonPath("$.bpi.USD.code").value("USD"))
                .andExpect(jsonPath("$.bpi.USD.rate").value("57,756.298"));
    }

    /**
     * 呼叫coindesk 轉換内容 api，測試内容是否如期待。
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    @Test
    public void trnCoindeskTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/trnCoindesk")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.time.updated").value("2024/09/02 07:07:20"))
                .andExpect(jsonPath("$.disclaimer").value("just for test"))
                .andExpect(jsonPath("$.chartName").value("Bitcoin"))
                .andExpect(jsonPath("$.bpi.USD.code").value("USD"))
                .andExpect(jsonPath("$.bpi.USD.幣別").value("美金"))
                .andExpect(jsonPath("$.bpi.USD.匯率").value(57756.2984));
    }
}