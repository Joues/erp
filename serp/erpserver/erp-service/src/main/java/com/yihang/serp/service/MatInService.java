package com.yihang.serp.service;

import com.yihang.serp.mapper.MatInMapper;
import com.yihang.serp.model.MatIn;
import com.yihang.serp.model.PurOrder;
import com.yihang.serp.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/4/6 9:57
 */
@Service
public class MatInService {
    @Autowired
    MatInMapper MatInMapper;

    public RespPageBean getMatInByPage(Integer page, Integer size, MatIn matIn, Date[] dateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<PurOrder> data = MatInMapper.getMatInByPage(page, size, matIn,dateScope);
        Long total = MatInMapper.getTotal(matIn,dateScope);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    public Integer addMatIn(MatIn matIn) {
        return MatInMapper.insertSelective(matIn);
    }

    public Integer updateMatIn(MatIn matIn) {
        return MatInMapper.updateByPrimaryKeySelective(matIn);
    }

    public Integer deleteMatInById(Integer id) {
        return MatInMapper.deleteByPrimaryKey(id);
    }
}
