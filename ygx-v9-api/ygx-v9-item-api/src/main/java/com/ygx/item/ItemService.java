package com.ygx.item;

import com.ygx.v9.pojo.ResultBean;

import java.util.List;

public interface ItemService {

    ResultBean createHtmlById(Long productId);

    ResultBean batchCreateHtml(List<Long> idList);
}
