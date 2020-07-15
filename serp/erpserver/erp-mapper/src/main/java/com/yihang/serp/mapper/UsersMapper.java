package com.yihang.serp.mapper;

import com.yihang.serp.model.Role;
import com.yihang.serp.model.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

    Users loadUserByUsername(String username);

    List<Role> getUsersRolesById(Integer id);

    List<Users> getAllUsers(@Param("id") Integer id, @Param("keywords") String keywords);
}