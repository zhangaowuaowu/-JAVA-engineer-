package com.cathay.java.controller;

import com.cathay.java.service.CoindeskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class CoindeskController {

    @Autowired
    private CoindeskService coindeskService;

    @GetMapping("/coindesk")
    public String getCoindesk() {
        String res = coindeskService.getCoindeskData();
        log.info(res);
        return res;
    }

    @GetMapping("/trnCoindesk")
    public String trnCoindesk() {
        String res = coindeskService.trnCoindeskData();
        log.info(res);
        return res;
    }

}
