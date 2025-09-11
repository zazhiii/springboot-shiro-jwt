package com.zazhi.service.impl;

import com.zazhi.common.JwtUtil;
import com.zazhi.mapper.UserMapper;
import com.zazhi.pojo.RoleAndPermission;
import com.zazhi.pojo.User;
import com.zazhi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lixh
 * @since 2025/9/11 16:48
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public String login(String username, String password) {
        User user = userMapper.getByUsername(username);
        if(user == null){
            throw new RuntimeException("用户不存在");
        }
        if(!user.getPassword().equals(password)){
            throw new RuntimeException("密码不正确");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("username", user.getUsername());
        return JwtUtil.genToken(map);
    }

    @Override
    public User getById(Integer userId) {
        return userMapper.getById(userId);
    }

    @Override
    public RoleAndPermission getRoleAndPermission(Integer userId) {
        return userMapper.getRolesAndPermissions(userId);
    }
}
