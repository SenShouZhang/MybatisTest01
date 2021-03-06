package com.itheima.test;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zhang
 * @date 2022/04/02
 */
public class Demo01Test {
    /**
     * 查询
     */
    @Test
    public void test01() {
        SqlSession sqlSession = MyBatisUtil.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> userAndRole = mapper.findUserAndRole(0, 2);
        for (User user : userAndRole) {
            System.err.println(user);
        }
        MyBatisUtil.commitAndClose(sqlSession);
    }

    /**
     * 新增
     */
    @Test
    public void test02() {
        SqlSession sqlSession = MyBatisUtil.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("wangwu");
        user.setEmail("wangwu@163.com");
        user.setPassword("123");
        mapper.insert(user);
        Integer userId = user.getId();
        ArrayList<Integer> roleIds = new ArrayList<>();
        Collections.addAll(roleIds, 1, 2, 3);
        user.setRoleIds(roleIds);
        mapper.insertUserRole(userId, user.getRoleIds());
        sqlSession.close();
    }

    /**
     * 修改
     * 根据用户id修改用户数据(包含该用户指定的角色)
     * 修改用户可以调用方法
     * 修改角色时，先删除对应用户所有角色，再添加其所有角色
     */
    @Test
    public void test03() {
        SqlSession sqlSession = MyBatisUtil.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(7);
        user.setUsername("wangba");
        user.setPassword("123456");
        user.setEmail("244324234@qq.com");
        mapper.updateById(user);
        mapper.deleteUserRole(user.getId());
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 3);
        user.setRoleIds(list);
        mapper.insertUserRole(user.getId(), user.getRoleIds());
        sqlSession.close();
    }

    /**
     * 删除
     * 需求: 根据id删除用户(以及对应的角色信息)
     */
    @Test
    public void test04() {
        SqlSession sqlSession = MyBatisUtil.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list, 2, 7);
        //删除时要先删除子表，再删除主表，否则会出现外键约束的情况
        mapper.deleteUserRoleBatchsIds(list);
        mapper.deleteBatchIds(list);
        sqlSession.close();
    }
}
