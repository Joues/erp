package com.yihang.serp.service;

import com.yihang.serp.mapper.PurOrderMapper;
import com.yihang.serp.model.PurOrder;
import com.yihang.serp.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/3/8 14:57
 */
@Service
public class PurOrderService {
    @Autowired
    PurOrderMapper PurOrderMapper;

    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    DecimalFormat decimalFormat = new DecimalFormat("##.00");

    public Integer addPurOrder(PurOrder purOrder) {
//        purOrder.setDate(new Date());
        return PurOrderMapper.insertSelective(purOrder);
    }

    public Integer updatePurOrder(PurOrder purOrder) {
        return PurOrderMapper.updateByPrimaryKeySelective(purOrder);
    }

    public Integer deletePurOrderById(Integer id) {
        return PurOrderMapper.deleteByPrimaryKey(id);
    }

    public RespPageBean getPurOrderByPage(Integer page, Integer size, PurOrder purOrder, Date[] dateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<PurOrder> data = PurOrderMapper.getPurOrderByPage(page, size, purOrder,dateScope);
        Long total = PurOrderMapper.getTotal(purOrder,dateScope);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    public Integer maxoid() {
        return PurOrderMapper.maxoid();
    }

    public Integer addPurs(List<PurOrder> list) {
        return PurOrderMapper.addPurs(list);
    }
}
