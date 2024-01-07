package com.yihang.serp.service;

import com.yihang.serp.mapper.StateMapper;
import com.yihang.serp.model.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/3/18 8:43
 */
@Service
public class StateService {
    @Autowired
    StateMapper StateMapper;

    public List<State> getAllStates() {
        return StateMapper.getAllStates();
    }
}
