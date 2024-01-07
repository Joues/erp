package com.yihang.serp.controller;

import com.yihang.serp.model.*;
import com.yihang.serp.service.*;
import com.yihang.serp.utils.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/3/8 14:49
 */
@RestController
@RequestMapping("/pur/order")
public class PurOrderController {
    @Autowired
    PurOrderService PurOrderService;

    @Autowired
    SupplierService SupplierService;

    @Autowired
    TypeService TypeService;

    @Autowired
    StoreService StoreService;

    @Autowired
    StateService StateService;

//    查询接口
//    @GetMapping("/")
//    public List<PurOrderMapper> getAllPurOrder() {
//        return PurOrderService.getAllPurOrder();
//    }

    @GetMapping("/")
    public RespPageBean getPurOrderByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, PurOrder purOrder, Date[] dateScope) {
        return PurOrderService.getPurOrderByPage(page, size, purOrder, dateScope);
    }

    @GetMapping("/supplier")
    public List<PurSupplier> getAllSuppliers() {
        return SupplierService.getAllSuppliers();
    }

    @GetMapping("/type")
    public List<Type> getAllTypes() {
        return TypeService.getAllTypes();
    }

    @GetMapping("/store")
    public List<Store> getAllStores() {
        return StoreService.getAllStores();
    }

    @GetMapping("/state")
    public List<State> getAllStates() {
        return StateService.getAllStates();
    }

    //    添加接口
    @PostMapping("/")
    public RespBean addPurOrder(@RequestBody PurOrder purOrder) {
        if (PurOrderService.addPurOrder(purOrder) == 1) {
            return RespBean.ok("您已成功添加了一条订单！");
        }
        return RespBean.error("添加订单失败！请联系管理员！");
    }

    //    修改接口
    @PutMapping("/")
    public RespBean updatePurOrder(@RequestBody PurOrder purOrder) {
        if (PurOrderService.updatePurOrder(purOrder) == 1) {
            return RespBean.ok("您已成功修改了一条订单！");
        }
        return RespBean.error("修改订单失败！请联系管理员！");
    }

    //    删除接口
    @DeleteMapping("/{id}")
    public RespBean deletePurOrderById(@PathVariable Integer id) {
        if (PurOrderService.deletePurOrderById(id) == 1) {
            return RespBean.ok("您已成功删除了一条订单！！");
        }
        return RespBean.error("删除订单失败！请联系管理员！");
    }

    @GetMapping("/maxoid")
    public RespBean maxoid() {
        RespBean respBean = RespBean.build().setStatus(200)
                .setObj(String.format("%08d", PurOrderService.maxoid() + 1));
        return respBean;
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportData() {
        List<PurOrder> list = (List<PurOrder>) PurOrderService.getPurOrderByPage(null, null, null, null).getData();
        return POIUtils.PurOrder2Excel(list);
    }

    @PostMapping("/import")
    public RespBean importData(MultipartFile file) throws IOException {
        List<PurOrder> list = POIUtils.excel2PurOrder(file, SupplierService.getAllSuppliers(), TypeService.getAllTypes(), StoreService.getAllStores(), StateService.getAllStates());
        if (PurOrderService.addPurs(list) == list.size()) {
            return RespBean.ok("上传成功");
        }
        return RespBean.error("上传失败");
    }
}