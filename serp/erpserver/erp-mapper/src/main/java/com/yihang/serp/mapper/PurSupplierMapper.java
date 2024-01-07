package com.yihang.serp.mapper;

import com.yihang.serp.model.PurOrder;
import com.yihang.serp.model.PurSupplier;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PurSupplierMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PurSupplier record);

    int insertSelective(PurSupplier record);

    PurSupplier selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PurSupplier record);

    int updateByPrimaryKey(PurSupplier record);

    List<PurSupplier> getAllSuppliers();

    List<PurOrder> getPurSupplierByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("keyword") PurSupplier purSupplier, @Param("dataScope") Date[] dateScope);

    Long getTotal(@Param("keyword") PurSupplier purSupplier, @Param("dateScope") Date[] dateScope);

    Integer maxId();
}