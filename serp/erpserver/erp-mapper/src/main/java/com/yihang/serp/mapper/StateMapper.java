package com.yihang.serp.mapper;

import com.yihang.serp.model.State;

import java.util.List;

public interface StateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(State record);

    int insertSelective(State record);

    State selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(State record);

    int updateByPrimaryKey(State record);

    List<State> getAllStates();
}