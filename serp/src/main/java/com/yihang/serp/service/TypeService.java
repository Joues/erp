package com.yihang.serp.service;

import com.yihang.serp.mapper.TypeMapper;
import com.yihang.serp.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/3/16 16:52
 */
@Service
public class TypeService {
    @Autowired
    TypeMapper TypeMapper;

    public List<Type> getAllTypes() {
        return TypeMapper.getAllTypes();
    }
}
