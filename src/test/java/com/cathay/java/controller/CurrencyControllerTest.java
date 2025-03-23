package com.cathay.java.controller;

import com.cathay.java.entity.CurrencyEntity;
import com.cathay.java.repository.CurrencyRepository;
import org.junit.Assert;
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
 * CRUD單元測試
 * @param
 * @return
 * @author 刁卓
 * Change History:
 * Last Modify author :刁卓 Date:  Version:1.0
 * change Description:
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CurrencyControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private CurrencyRepository currencyRepository;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * 顯示全部資料api，測試資料是否正常
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    @Test
    public void getAllCurrencyTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/currency/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.msg").value("success"))
                .andExpect(jsonPath("$.data.size()").value(9))
                .andExpect(jsonPath("$.data[0].currencyCode").value("USD"))
                .andExpect(jsonPath("$.data[0].currencyName").value("美元"));
    }

    /**
     * 更新USD->USA,美元->美國貨幣，測試資料更新是否正常
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    @Test
    public void getUpdateTest() throws Exception {

        CurrencyEntity entity = new CurrencyEntity();
        entity.setId(1);
        entity.setCurrencyCode("USA");
        entity.setCurrencyName("美國貨幣");
        entity.setCreateName("Andy");
        currencyRepository.save(entity);

        Assert.assertEquals("美國貨幣", currencyRepository.findByCurrencyCode("USA").getCurrencyName());
    }

    /**
     * 新增ABC,某國貨幣，測試資料新增是否正常
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    @Test
    public void getAddTest() throws Exception {

        CurrencyEntity entity = new CurrencyEntity();
        entity.setCurrencyCode("ABC");
        entity.setCurrencyName("某國貨幣");
        entity.setCreateName("Andy");
        currencyRepository.save(entity);

        Assert.assertEquals("某國貨幣", currencyRepository.findByCurrencyCode("ABC").getCurrencyName());
    }

    /**
     * 刪除CurrencyCode為USA的資料，測試資料庫全部資料是否為9個
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    @Test
    public void getDeleteTest() throws Exception {

        currencyRepository.deleteByCurrencyCode("USA");

        Assert.assertEquals(9, currencyRepository.findAll().size());
    }
}