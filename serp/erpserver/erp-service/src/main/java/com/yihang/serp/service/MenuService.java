package com.yihang.serp.service;

import com.yihang.serp.mapper.MenuMapper;
import com.yihang.serp.model.Menu;
import com.yihang.serp.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/2/24 14:30
 */
@Service
public class MenuService {
    @Autowired
    MenuMapper menuMapper;

    public List<Menu> getMenuByUsersId() {
        return menuMapper.getMenuByUsersId(((Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }

//    public List<Menu> getAllMenus() {
//        return menuMapper.getAllMenus();
//    }
//
//    public List<Integer> getMidsByRid(Integer rid) {
//        return menuMapper.getMidsByRid(rid);
//    }
//
//    @Transactional
//    public boolean updateMenuRole(Integer rid, Integer[] mids) {
//        menuRoleMapper.deleteByRid(rid);
//        if (mids == null || mids.length == 0) {
//            return true;
//        }
//        Integer result = menuRoleMapper.insertRecord(rid, mids);
//        return result==mids.length;
//    }
}
