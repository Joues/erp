package com.yihang.serp.mapper;

import com.yihang.serp.model.MatIn;
import com.yihang.serp.model.PurOrder;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MatInMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MatIn record);

    int insertSelective(MatIn record);

    MatIn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MatIn record);

    int updateByPrimaryKey(MatIn record);

    List<PurOrder> getMatInByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("keyword") MatIn matIn, @Param("dateScope") Date[] dateScope);

    Long getTotal(@Param("keyword") MatIn matIn, @Param("dateScope") Date[] dateScope);
}