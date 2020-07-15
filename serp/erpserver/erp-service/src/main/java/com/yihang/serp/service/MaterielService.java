package com.yihang.serp.service;

import com.yihang.serp.mapper.MaterielMapper;
import com.yihang.serp.model.MatIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/4/6 11:22
 */
@Service
public class MaterielService {
    @Autowired
    MaterielMapper MaterielMapper;

    public List<MatIn> getAllMateriel() {
        return MaterielMapper.getAllMateriel();
    }
}
