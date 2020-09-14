package com.ygx.api.search;

import com.ygx.entity.TProduct;
import com.ygx.v9.pojo.ResultBean;

import java.util.List;

public interface ISearchService {

    ResultBean initAllData();

    ResultBean updateById(Long id);

    List<TProduct> searchByKeyWord(String keyWord);


}
