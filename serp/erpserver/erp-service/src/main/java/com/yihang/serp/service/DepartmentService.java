package com.yihang.serp.service;

import com.yihang.serp.mapper.DepartmentMapper;
import com.yihang.serp.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/4/29 10:41
 */
@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    public List<Department> getallDepartment() {
        return departmentMapper.getAllDepartmentsByParentId(-1);
    }

    public void addDepartment(Department dep) {
        dep.setEnabled(true);
        departmentMapper.addDepartment(dep);
    }

    public void deleteDepartmentById(Department dep) {
        departmentMapper.deleteDepartmentById(dep);
    }

    public List<Department> getAllDepartmentsWithOutChildren() {
        return departmentMapper.getAllDepartmentsWithOutChildren();
    }
}
