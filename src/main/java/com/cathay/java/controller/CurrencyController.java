package com.cathay.java.controller;

import com.cathay.java.entity.CurrencyEntity;
import com.cathay.java.pojo.CurrencyDTO;
import com.cathay.java.result.Result;
import com.cathay.java.service.CurrencyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currency")

@Slf4j
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    /**
     * 查詢所有資料
     * @param
     * @return currencyEntityList
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    @GetMapping("/all")
    public Result<List<CurrencyEntity>> getAll() {
        List<CurrencyEntity> currencyEntityList = currencyService.getAll();
        return Result.success(currencyEntityList);
    }

    /**
     * 更新幣別ById
     * @param dto
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    @PutMapping("/update")
    @Transactional(rollbackFor = Exception.class)
    public Result update(@RequestBody CurrencyDTO dto) {
        currencyService.updateById(dto);
        return Result.success();
    }

    /**
     * 新增幣別
     * @param dto
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    public Result addCurrency(@RequestBody CurrencyDTO dto) {
        currencyService.add(dto);
        return Result.success();
    }

    /**
     * 刪除幣別ByCode
     * @param currencyCode
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    @DeleteMapping("delect/{currencyCode}")
    @Transactional(rollbackFor = Exception.class)
    public Result delete(@PathVariable String currencyCode) {
        currencyService.deleteByCurrencyCode(currencyCode);
        return Result.success();
    }
}
