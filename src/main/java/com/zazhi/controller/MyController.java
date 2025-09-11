package com.zazhi.controller;

import com.zazhi.common.Result;
import com.zazhi.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
@RequiredArgsConstructor
public class MyController {
    private final UserService userService;

    @GetMapping("/login")
    public Result<String> login(String username, String password) {
        return Result.success(userService.login(username, password));
    }

    @GetMapping("/public")
    public Result<String> pub() {
        log.info("调用 pub");
        return Result.success("公共页面, 不需要登录就能访问~");
    }

    // 需要「登录认证」才能访问
    @RequiresAuthentication
    @GetMapping("/profile")
    public Result<String> profile() {
        return Result.success("个人信息页面，登录才能访问");
    }

    // 需要「登录认证」和「角色校验」才能访问
    @RequiresAuthentication
    @RequiresRoles("ROLE-A")
    @GetMapping("/role-a")
    public Result<String> requireRoleA() {
        log.info(">>> 调用需要 A 角色的接口");
        return Result.success("A 角色可以访问的页面");
    }
    @RequiresAuthentication
    @RequiresRoles("ROLE-B")
    @GetMapping("/role-b")
    public Result<String> requireRoleB() {
        log.info(">>> 调用需要 B 角色的接口");
        return Result.success("B 角色可以访问的页面");
    }

    // 需要「登录认证」和「权限校验」才能访问
    @RequiresAuthentication
    @RequiresPermissions("PERMISSION-A")
    @GetMapping("/permission-a")
    public Result<String> requirePermissionA() {
        log.info(">>> 调用需要 A 权限的接口");
        return Result.success("需要 A 权限的页面");
    }
    @RequiresAuthentication
    @RequiresPermissions("PERMISSION-B")
    @GetMapping("/permission-b")
    public Result<String> requirePermissionB() {
        log.info(">>> 调用需要 B 权限的接口");
        return Result.success("需要 B 权限的页面");
    }
}
