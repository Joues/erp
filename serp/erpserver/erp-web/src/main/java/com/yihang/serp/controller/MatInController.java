package com.yihang.serp.controller;

import com.yihang.serp.model.MatIn;
import com.yihang.serp.model.RespBean;
import com.yihang.serp.model.RespPageBean;
import com.yihang.serp.service.MatInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/4/6 9:45
 */
@RestController
@RequestMapping("/mat/in")
public class MatInController {
    @Autowired
    MatInService MatInService;

    @Autowired
    com.yihang.serp.service.MaterielService MaterielService;

    @GetMapping("/")
    public RespPageBean getMatInByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, MatIn matIn, Date[] dateScope) {
        return MatInService.getMatInByPage(page, size, matIn, dateScope);
    }

    @GetMapping("/materiel")
    public List<MatIn> getAllMateriel() {
        return MaterielService.getAllMateriel();
    }

    //    添加接口
    @PostMapping("/")
    public RespBean addMatIn(@RequestBody MatIn matIn) {
        if (MatInService.addMatIn(matIn) == 1) {
            return RespBean.ok("您已成功添加了一条订单！");
        }
        return RespBean.error("添加订单失败！请联系管理员！");
    }

    //    修改接口
    @PutMapping("/")
    public RespBean updateMatIn(@RequestBody MatIn matIn) {
        if (MatInService.updateMatIn(matIn) == 1) {
            return RespBean.ok("您已成功修改了一条订单！");
        }
        return RespBean.error("修改订单失败！请联系管理员！");
    }

    //    删除接口
    @DeleteMapping("/{id}")
    public RespBean deleteMatInById(@PathVariable Integer id) {
        if (MatInService.deleteMatInById(id) == 1) {
            return RespBean.ok("您已成功删除了一条订单！！");
        }
        return RespBean.error("删除订单失败！请联系管理员！");
    }
}
