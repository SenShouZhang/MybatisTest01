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
}
