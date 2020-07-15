package com.yihang.serp.service;

import com.yihang.serp.mapper.PurPriceMapper;
import com.yihang.serp.model.PurPrice;
import com.yihang.serp.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/3/23 14:53
 */
@Service
public class PurPriceService {
    @Autowired
    PurPriceMapper PurPriceMapper;

    public RespPageBean getPurPriceByPage(Integer page, Integer size, PurPrice purPrice, Date[] dateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<PurPrice> data = PurPriceMapper.getPurPriceByPage( page, size, purPrice, dateScope);
        Long total = PurPriceMapper.getTotal(purPrice, dateScope);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    public Integer addPurPrice(PurPrice purPrice) {
        return PurPriceMapper.insertSelective(purPrice);
    }

    public Integer updatePurPrice(PurPrice purPrice) {
        return PurPriceMapper.updateByPrimaryKeySelective(purPrice);
    }

    public Integer deletePurPrice(Integer id) {
        return PurPriceMapper.deleteByPrimaryKey(id);
    }

}
