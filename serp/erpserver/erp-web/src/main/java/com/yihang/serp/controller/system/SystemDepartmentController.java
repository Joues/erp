package com.yihang.serp.controller.system;

import com.yihang.serp.model.Department;
import com.yihang.serp.model.RespBean;
import com.yihang.serp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/4/29 10:38
 */
@RestController
@RequestMapping("/system/department")
public class SystemDepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/")
    public List<Department> getallDepartment() {
        return departmentService.getallDepartment();
    }

    @PostMapping("/")
    public RespBean addDep(@RequestBody Department dep) {
        departmentService.addDepartment(dep);
        if (dep.getResult() == 1) {
            return RespBean.ok("添加成功！", dep);
        }
        return RespBean.error("添加失败，请联系管理员！");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteDepById(@PathVariable Integer id) {
        Department dep = new Department();
        dep.setId(id);
        departmentService.deleteDepartmentById(dep);
        if (dep.getResult() == -2) {
            return RespBean.error("该部门下有子部门，删除失败！");
        } else if (dep.getResult() == -1) {
            return RespBean.error("该部门下有员工，删除失败！");
        } else if (dep.getResult() == 1) {
            return RespBean.ok("删除成功！");
        }
        return RespBean.error("删除失败，请联系管理员！");
    }
}
