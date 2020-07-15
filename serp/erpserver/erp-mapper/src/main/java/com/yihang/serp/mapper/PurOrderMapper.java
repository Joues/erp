package com.yihang.serp.mapper;

import com.yihang.serp.model.PurOrder;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PurOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PurOrder record);

    int insertSelective(PurOrder record);

    PurOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PurOrder record);

    int updateByPrimaryKey(PurOrder record);

    List<PurOrder> getPurOrderByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("keyword") PurOrder purOrder, @Param("dateScope") Date[] dateScope);

    Long getTotal(@Param("keyword") PurOrder purOrder,@Param("dateScope") Date[] dateScope);

    Integer maxoid();

    Integer addPurs(@Param("list") List<PurOrder> list);
}