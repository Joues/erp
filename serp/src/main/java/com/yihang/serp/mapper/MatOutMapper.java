package com.yihang.serp.mapper;

import com.yihang.serp.model.MatOut;

public interface MatOutMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MatOut record);

    int insertSelective(MatOut record);

    MatOut selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MatOut record);

    int updateByPrimaryKey(MatOut record);
}