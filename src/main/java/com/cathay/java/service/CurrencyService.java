package com.cathay.java.service;

import com.cathay.java.entity.CurrencyEntity;
import com.cathay.java.pojo.CurrencyDTO;

import java.util.List;

public interface CurrencyService {
    List<CurrencyEntity> getAll();

    void updateById(CurrencyDTO dto);

    void add(CurrencyDTO dto);

    void deleteByCurrencyCode(String currencyCode);
}
