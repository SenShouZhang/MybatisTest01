package com.itheima.mapper;

import com.itheima.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * 需求: 查询前两个用户的信息以及对应的角色信息
 * 需求：增加一条用户数据(包含该用户指定的角色)
 * 根据用户id修改用户数据(包含该用户指定的角色)
 * 需求: 根据id删除用户(以及对应的角色信息)
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
     * @param uid     用户id
     * @param roleIds 角色id
     * @return int
     */
    int insertUserRole(@Param("uid") Integer uid, @Param("roleIds") List<Integer> roleIds);

    /**
     * 根据用户id修改用户数据(包含该用户指定的角色)
     * 删除对应用户id的所有角色
     *
     * @param uid 用户id
     * @return int
     */
    int deleteUserRole(Integer uid);

    /**
     * 成组删除对应的用户角色
     *
     * @param uids 用户名数组
     * @return int
     */
    int deleteUserRoleBatchsIds(@Param("uids") ArrayList<Integer> uids);
}




