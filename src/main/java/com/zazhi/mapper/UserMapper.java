package com.zazhi.mapper;

import com.zazhi.pojo.RoleAndPermission;
import com.zazhi.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 *
 * @author lixh
 * @since 2025/9/11 16:49
 */
@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User getByUsername(String username);

    @Select("select * from user where id = #{id}")
    User getById(Integer userId);

    RoleAndPermission getRolesAndPermissions(Integer userId);
}
