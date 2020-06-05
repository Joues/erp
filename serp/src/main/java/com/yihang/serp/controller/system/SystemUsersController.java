package com.yihang.serp.controller.system;

import com.yihang.serp.model.RespBean;
import com.yihang.serp.model.Role;
import com.yihang.serp.model.Users;
import com.yihang.serp.service.RoleService;
import com.yihang.serp.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/users")
public class SystemUsersController {
    @Autowired
    UsersService UsersService;
    @Autowired
    RoleService roleService;

    @GetMapping("/")
    public List<Users> getAllUsers(String keywords) {
        return UsersService.getAllUsers(keywords);
    }

    @PostMapping("/")
    public RespBean addUsers(@RequestBody Users users) {
        if (UsersService.addUsers(users) == 1) {
            return RespBean.ok("新用户添加成功！");
        }
        return RespBean.error("添加失败，请联系管理员！");
    }

    @PutMapping("/")
    public RespBean updateUsers(@RequestBody Users users) {
        if (UsersService.updateUsers(users) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
    @GetMapping("/roles")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PutMapping("/role")
    public RespBean updateUsersRole(Integer usersid, Integer[] uids) {
        if (UsersService.updateUsersRole(usersid, uids)) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteUsersById(@PathVariable Integer id) {
        if (UsersService.deleteUsersById(id) == 1) {
            return RespBean.ok("删除成功!");
        }
        return RespBean.error("删除失败!");
    }
}
