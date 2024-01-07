package com.yihang.serp.mapper;

import com.yihang.serp.model.Department;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> getAllDepartmentsByParentId(Integer i);

    void addDepartment(Department dep);

    void deleteDepartmentById(Department dep);

    List<Department> getAllDepartmentsWithOutChildren();
}