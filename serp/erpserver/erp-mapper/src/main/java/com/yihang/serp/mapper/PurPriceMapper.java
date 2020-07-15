package com.yihang.serp.mapper;

import com.yihang.serp.model.PurPrice;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PurPriceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PurPrice record);

    int insertSelective(PurPrice record);

    PurPrice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PurPrice record);

    int updateByPrimaryKey(PurPrice record);

    List<PurPrice> getPurPriceByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("keyword") PurPrice purPrice, @Param("dateScope") Date[] dateScope);

    Long getTotal(@Param("keyword") PurPrice purPrice,@Param("dateScope") Date[] dateScope);
}