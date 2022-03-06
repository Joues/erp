package com.yihang.serp.service;

import com.yihang.serp.mapper.PurSupplierMapper;
import com.yihang.serp.model.PurOrder;
import com.yihang.serp.model.PurSupplier;
import com.yihang.serp.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/3/16 16:26
 */
@Service
public class SupplierService {
    @Autowired
    PurSupplierMapper PurSupplierMapper;

    public List<PurSupplier> getAllSuppliers() {
        return PurSupplierMapper.getAllSuppliers();
    }

    public RespPageBean getPurSupplierByPage(Integer page, Integer size, PurSupplier purSupplier, Date[] dateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<PurOrder> data = PurSupplierMapper.getPurSupplierByPage(page, size, purSupplier,dateScope);
        Long total = PurSupplierMapper.getTotal(purSupplier,dateScope);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    public Integer addPurSupplier(PurSupplier purSupplier) {
        return PurSupplierMapper.insertSelective(purSupplier);
    }

    public Integer updatePurSupplier(PurSupplier purSupplier) {
        return PurSupplierMapper.updateByPrimaryKeySelective(purSupplier);
    }

    public Integer deletePurSupplier(Integer id) {
        return PurSupplierMapper.deleteByPrimaryKey(id);
    }

    public Integer maxId() {
        return PurSupplierMapper.maxId();
    }
}
