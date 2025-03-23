package com.cathay.java.service.impl;

import com.cathay.java.entity.CurrencyEntity;
import com.cathay.java.pojo.CurrencyDTO;
import com.cathay.java.repository.CurrencyRepository;
import com.cathay.java.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public List<CurrencyEntity> getAll() {
        return currencyRepository.findAll();
    }

    @Override
    public void updateById(CurrencyDTO dto) {
        Optional<CurrencyEntity> optional = currencyRepository.findById(dto.getId());

        if (!optional.isPresent()) {
            throw new RuntimeException("未發現幣別：" + dto.getCurrencyName());
        }

        CurrencyEntity entity = optional.get();
        entity.setCurrencyCode(dto.getCurrencyCode());
        entity.setCurrencyName(dto.getCurrencyName());

        currencyRepository.save(entity);
    }

    @Override
    public void add(CurrencyDTO dto) {
        if (currencyRepository.existsByCurrencyCode(dto.getCurrencyCode())) {
            throw new RuntimeException("幣別已存在：" + dto.getCurrencyName());
        }

        CurrencyEntity entity = new CurrencyEntity();
        entity.setCurrencyCode(dto.getCurrencyCode());
        entity.setCurrencyName(dto.getCurrencyName());

        currencyRepository.save(entity);
    }

    @Override
    public void deleteByCurrencyCode(String currencyCode) {

        if (!currencyRepository.existsByCurrencyCode(currencyCode)) {
                throw new RuntimeException("找不到對應的幣別代碼：" + currencyCode);
        }
        currencyRepository.deleteByCurrencyCode(currencyCode);
    }
}
