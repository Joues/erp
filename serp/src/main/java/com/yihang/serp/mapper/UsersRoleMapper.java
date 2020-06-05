package com.yihang.serp.mapper;

import com.yihang.serp.model.UsersRole;
import org.apache.ibatis.annotations.Param;

public interface UsersRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UsersRole record);

    int insertSelective(UsersRole record);

    UsersRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UsersRole record);

    int updateByPrimaryKey(UsersRole record);

    Integer addRole(@Param("usersid") Integer usersid, @Param("uids") Integer[] uids);

    void deleteByUsersid(Integer usersid);
}