package com.zazhi.shiro;

import com.zazhi.common.JwtUtil;
import com.zazhi.pojo.RoleAndPermission;
import com.zazhi.pojo.User;
import com.zazhi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountRealm extends AuthorizingRealm {
    private final UserService userService;

    // 这个方法用于判断 AccountRealm 是否支持该类型的 Token。
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    // 用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Integer userId = (Integer) principals.getPrimaryPrincipal();

        // 这里查询数据库获取用户的角色和权限
        RoleAndPermission roleAndPermission = userService.getRoleAndPermission(userId);

        // 并使用 addRoles 和 addStringPermissions 方法将角色和权限添加到授权信息中。
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roleAndPermission.getRoles());
        authorizationInfo.setStringPermissions(roleAndPermission.getPermissions());
        return authorizationInfo;
    }

    // 用于验证用户身份
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken){
        log.info(">>> verify the jwt token");

        JwtToken token = (JwtToken)authenticationToken;
        String jwtToken = (String) token.getPrincipal();

        // 这里是真正验证 JwtToken 是否有效的地方
        // 如果验证失败，抛出 AuthenticationException 异常。这个请求会被认定为未认证请求。后续返回给前端
        Map<String, Object> map;
        try {
             map = JwtUtil.parseToken(jwtToken);
        } catch (Exception e) {
            throw new AuthenticationException("该token非法，可能被篡改或过期");
        }
        Integer userId = (Integer) map.get("id");

        // 这里可以根据业务逻辑自定义验证逻辑
        // 例如：1.根据用户名查询数据库，判断用户是否存在
        User user = userService.getById(userId);
        if(user == null){
            throw new RuntimeException("用户不存在");
        }
        //      2.判断用户状态是否被锁定等
        // 若校验失败，抛出异常即可。
        return new SimpleAuthenticationInfo(userId, jwtToken, getName());
    }
}
