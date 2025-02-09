package com.zazhi.shiro_demo.service;

import com.zazhi.shiro_demo.common.JwtUtil;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

/**
 * @author zazhi
 * @date 2024/12/9
 * @description: TODO
 */
@Service
public class UserService {
    public String login(String username, String password) {
        // 判断逻辑省略
        return JwtUtil.genToken(Map.of("username", username));
    }

    public Set<String> findPermissionsByUsername(String username) {
        return Set.of("user:delete", "user:update");
    }

    public Set<String> findRolesByUsername(String username) {
        // 模拟从数据库中查询用户角色
        if(username.equals("admin")){
            return Set.of("admin");
        }
        return Set.of("user");
    }
}
