package com.yihang.serp.controller;

import com.yihang.serp.bean.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/2/18 11:25
 */
@RestController
@Api(tags = "用户数据接口")
public class UserController {
    @ApiOperation(value = "查询用户", notes = "根据用户id查询用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, defaultValue = "99")
    @GetMapping("/user")
    public User getUserById(Integer id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    @ApiOperation(value = "删除用户", notes = "根据用户id删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, defaultValue = "99")
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功"),
            @ApiResponse(code = 500, message = "删除失败")
    })
    @DeleteMapping("/user/{id}")
    public void deleteUserById(@PathVariable Integer id) {
        System.out.println("deleteUserById:" + id);
    }

    @PutMapping("/user")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, defaultValue = "99"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, defaultValue = "yihang")
    })
    @ApiOperation(value = "更新用户", notes = "根据用户id更新用户名")
//    @ApiIgnore
    public User updateUsernameById(String username, Integer id) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        return user;
    }

    @PostMapping("/user")
    @ApiOperation(value = "添加用户", notes = "添加用户接口")
    public User addUser(@RequestBody User user) {
        return user;
    }
}
