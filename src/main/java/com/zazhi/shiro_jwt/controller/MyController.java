package com.zazhi.shiro_jwt.controller;

import com.zazhi.shiro_jwt.common.Result;
import com.zazhi.shiro_jwt.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zazhi
 * @date 2024/12/9
 * @description: 模拟用户登录和权限控制的接口
 */
@Slf4j
@RestController()
@RequestMapping("/api")
@Tag(name = "MyController", description = "用户接口")
public class MyController {

    @Autowired
    UserService userService;

    // 模拟登录
    @GetMapping("/login")
    public String login(String username, String password) {
        return userService.login(username, password);
    }

    // 不需要认证就能访问
    @GetMapping("/public")
    public Result<String> pub() {
        log.info("调用 pub");
        return Result.success("公共页面");
    }

    // 需要「认证」才能访问
    @RequiresAuthentication
    @GetMapping("/profile")
    public Result<String> profile() {
        return Result.success("个人信息页面");
    }

    // 需要「认证」和「特定角色」才能访问
    @RequiresAuthentication
    @RequiresRoles("admin")
    @GetMapping("/dashboard")
    public Result<String> dashboard() {
        log.info("调用 dashboard");
        return Result.success("控制面板页面");
    }

    // 需要「认证」和「特定权限」才能访问
    @RequiresAuthentication
    @RequiresPermissions("view:dashboard")
    @GetMapping("/viewDashboard")
    public Result<String> viewDashboard() {
        return Result.success("查看控制面板页面");
    }
}
