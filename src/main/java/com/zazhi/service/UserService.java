package com.zazhi.service;

import com.zazhi.pojo.RoleAndPermission;
import com.zazhi.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @author zazhi
 * @date 2024/12/9
 * @description: TODO
 */
@Service
public interface UserService {
    /**
     *
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    /**
     *
     * @param userId
     * @return
     */
    User getById(Integer userId);

    /**
     *
     * @param userId
     * @return
     */
    RoleAndPermission getRoleAndPermission(Integer userId);
}
