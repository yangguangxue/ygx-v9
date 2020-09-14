package com.ygx.ms.service;

import com.ygx.ms.entity.TProduct;

public interface IMiaoShaService {
    TProduct getById(Long id);

    boolean saleById(Long id);
}
