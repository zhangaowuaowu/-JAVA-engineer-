package com.cathay.java.service.impl;

import com.cathay.java.service.CoindeskService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Service
public class CoindeskServiceImpl implements CoindeskService {

    @Autowired
    private WebClient webClient;

    @Value("${coindesk.url}")
     private String coindeskUrl;

    private static final Map<String, String> CURRENCY_MAP = new HashMap<>();
    static {
        CURRENCY_MAP.put("USD", "美金");
        CURRENCY_MAP.put("GBP", "英鎊");
        CURRENCY_MAP.put("EUR", "歐元");
    }

    /**
     * 獲取coindesk api内容
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    @Override
    public String getCoindeskData() {
        return webClient.get()
                .uri(coindeskUrl)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    /**
     * coindesk資料轉換api
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    @Override
    public String trnCoindeskData() {
        String res =  webClient.get()
                .uri(coindeskUrl)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return this.transformJson(res);
    }

    /**
     * coindesk轉換方法
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    private String transformJson(String res) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(res);

            ObjectNode timeNode = (ObjectNode) rootNode.get("time");
            String updatedISO = timeNode.get("updatedISO").asText();
            DateTimeFormatter inputFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            ZonedDateTime parsedTime = ZonedDateTime.parse(updatedISO, inputFormatter);
            String formattedTime = parsedTime.format(outputFormatter);
            timeNode.put("updated", formattedTime);
            timeNode.put("updatedISO", formattedTime);
            timeNode.put("updateduk", formattedTime);

            ObjectNode bpiNode = (ObjectNode) rootNode.get("bpi");
            for (String currency : CURRENCY_MAP.keySet()) {
                if (bpiNode.has(currency)) {
                    ObjectNode currencyNode = (ObjectNode) bpiNode.get(currency);
                    currencyNode.put("幣別", CURRENCY_MAP.get(currency));
                    currencyNode.put("匯率", currencyNode.get("rate_float").asDouble());
                    ((ObjectNode) bpiNode.get(currency)).remove("symbol");
                    ((ObjectNode) bpiNode.get(currency)).remove("rate_float");
                }
            }

            return objectMapper.writeValueAsString(rootNode);
        } catch (Exception e) {
            throw new RuntimeException("JSON 轉換錯誤", e);
        }
    }
}
