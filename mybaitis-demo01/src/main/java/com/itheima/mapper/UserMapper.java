package com.itheima.mapper;

import com.itheima.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 需求: 查询前两个用户的信息以及对应的角色信息
 * 需求：增加一条用户数据(包含该用户指定的角色)
 *
 * @author zhang
 * @description 针对表【t_user】的数据库操作Mapper
 * @createDate 2022-04-02 20:20:53
 * @Entity com.itheima.pojo.User
 */
public interface UserMapper extends BaseMapper<User> {
    /**
     * 需求: 查询前两个用户的信息以及对应的角色信息
     *
     * @param index limit 起始索引
     * @param count limit 限定对象个数
     * @return {@link List}<{@link User}>
     */
    List<User> findUserAndRole(@Param("index") Integer index, @Param("count") Integer count);

    /**
     * 需求：增加一条用户数据(包含该用户指定的角色)
     * 添加角色
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return int
     */
    int insertUserRole(@Param("user_id") Integer userId, @Param("role_id") List<Integer> roleId);
}




