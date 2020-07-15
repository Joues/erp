package com.yihang.serp.controller;

import com.yihang.serp.model.PurPrice;
import com.yihang.serp.model.RespBean;
import com.yihang.serp.model.RespPageBean;
import com.yihang.serp.service.PurPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/3/23 14:50
 */
@RestController
@RequestMapping("/pur/price")
public class PurPriceController {
    @Autowired
    PurPriceService PurPriceService;

//    分页查询
    @GetMapping("/")
    public RespPageBean getPurPriceByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, PurPrice purPrice, Date[] dateScope) {
        return PurPriceService.getPurPriceByPage(page, size, purPrice, dateScope);
    }

    @PostMapping("/")
    public RespBean addPurPrice(@RequestBody PurPrice purPrice) {
        if (PurPriceService.addPurPrice(purPrice) == 1) {
            return RespBean.ok("报价单添加成功！");
        }
        return RespBean.error("添加失败，请联系管理员！");
    }

    @PutMapping("/")
    public RespBean updatePurPrice(@RequestBody PurPrice purPrice) {
        if (PurPriceService.updatePurPrice(purPrice) == 1) {
            return RespBean.ok("报价单修改成功！");
        }
        return RespBean.error("修改失败，请联系管理员！");
    }

    @DeleteMapping("/{id}")
    public RespBean deletePurPrice(@PathVariable Integer id) {
        if (PurPriceService.deletePurPrice(id) == 1) {
            return RespBean.ok("报价单删除成功！");
        }
        return RespBean.error("删除操作失败，请联系管理员！");
    }
}
