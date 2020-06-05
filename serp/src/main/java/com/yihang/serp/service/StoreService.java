package com.yihang.serp.service;

import com.yihang.serp.mapper.StoreMapper;
import com.yihang.serp.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/3/16 16:55
 */
@Service
public class StoreService {
    @Autowired
    StoreMapper StoreMapper;

    public List<Store> getAllStores() {
        return StoreMapper.getAllStores();
    }
}
