package com.zazhi.shiro_jwt.service;

import com.zazhi.shiro_jwt.common.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * @author zazhi
 * @date 2024/12/9
 * @description: TODO
 */
@Service
@Slf4j
public class UserService {
    public String login(String username, String password) {
        // 判断逻辑省略
        return JwtUtil.genToken(Map.of("username", username));
    }

    public Set<String> findPermissionsByUsername(String username) {
        log.info("查询权限: {}", username);
        return Set.of("user:delete", "user:update");
    }

    public Set<String> findRolesByUsername(String username) {
        log.info("查询角色: {}", username);
        if(username.equals("admin")){
            return Set.of("admin");
        }
        return Set.of("user");
    }
}
