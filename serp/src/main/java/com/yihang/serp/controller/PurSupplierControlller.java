package com.yihang.serp.controller;

import com.yihang.serp.model.PurSupplier;
import com.yihang.serp.model.RespBean;
import com.yihang.serp.model.RespPageBean;
import com.yihang.serp.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/3/21 9:46
 */
@RestController
@RequestMapping("/pur/supplier")
public class PurSupplierControlller {
    @Autowired
    SupplierService SupplierService;

    @GetMapping("/")
    public RespPageBean getPurSupplierByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, PurSupplier purSupplier, Date[] dateScope) {
        return SupplierService.getPurSupplierByPage(page, size, purSupplier, dateScope);
    }

    @PostMapping("/")
    public RespBean addPurSupplier(@RequestBody PurSupplier purSupplier){
        if (SupplierService.addPurSupplier(purSupplier) == 1) {
            return RespBean.ok("您成功添加了一条供应商信息！");
        }
        return RespBean.error("供应商信息添加失败！请联系管理员！");
    }

    @PutMapping("/")
    public RespBean updatePurSupplier(@RequestBody PurSupplier purSupplier) {
        if (SupplierService.updatePurSupplier(purSupplier) == 1) {
            return RespBean.ok("供应商信息修改成功！");
        }
        return RespBean.error("供应商信息修改失败！请联系管理员！");
    }

    @DeleteMapping("/{id}")
    public RespBean deletePurSupplierById(@PathVariable Integer id) {
        if (SupplierService.deletePurSupplier(id) == 1) {
            return RespBean.ok("供应商删除成功！");
        }
        return RespBean.error("供应商删除失败！请联系管理员！");
    }

    @GetMapping("/maxId")
    public RespBean maxId() {
        RespBean respBean = RespBean.build().setStatus(200)
                .setObj(String.format("%08d",SupplierService.maxId() +1));
        return respBean;
    }
}
