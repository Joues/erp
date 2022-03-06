package com.yihang.serp.service;

import com.yihang.serp.mapper.UsersMapper;
import com.yihang.serp.mapper.UsersRoleMapper;
import com.yihang.serp.model.Users;
import com.yihang.serp.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/2/22 16:25
 */
@Service
public class UsersService implements UserDetailsService {
    @Autowired
    UsersMapper UsersMapper;

    @Autowired
    UsersRoleMapper UsersRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = UsersMapper.loadUserByUsername(username);
        if (users==null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }
        users.setRoles(UsersMapper.getUsersRolesById(users.getId()));
        return users;
    }

    public List<Users> getAllUsers(String keywords) {
        return UsersMapper.getAllUsers(UserUtils.getCurrentUser().getId(),keywords);
    }

    public Integer updateUsers(Users users) {
        return UsersMapper.updateByPrimaryKeySelective(users);
    }

    @Transactional
    public boolean updateUsersRole(Integer usersid, Integer[] uids) {
        UsersRoleMapper.deleteByUsersid(usersid);
        return UsersRoleMapper.addRole(usersid, uids) == uids.length;
    }

    public Integer deleteUsersById(Integer id) {
        return UsersMapper.deleteByPrimaryKey(id);
    }

    public Integer addUsers(Users users) {
        return UsersMapper.insert(users);
    }
}