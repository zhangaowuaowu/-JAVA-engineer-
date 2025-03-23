package com.cathay.java.repository;

import com.cathay.java.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Integer> {
    boolean existsByCurrencyCode(String currencyCode);

    @Transactional(rollbackFor = Exception.class)
    void deleteByCurrencyCode(String currencyCode);

    CurrencyEntity findByCurrencyCode(String currencyCode);
}
