package com.itheima.util;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhang
 * @date 2022/04/02
 */
public class MyBatisUtil {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        //TODO: 1.从xml文件中构建SqlSessionFactory (读取 mybatis-config.xml 核心配置文件 并解析: 类似于dom4j)
        try {
            String resource = "mybatis-config.xml";
            //加载核心配置文件获取输入流(从类路径下加载指定文件名: resources目录 )
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new MybatisSqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 传入true,表示自动提交事务,传入false,手动提交
     *
     * @param flag true 为自动提交 默认为false 为手动提交
     * @return {@link SqlSession}
     */
    public static SqlSession openSession(boolean flag) {
        SqlSession session = sqlSessionFactory.openSession(flag);
        return session;
    }


    /**
     * 手动提交事务
     *
     * @return {@link SqlSession}
     */
    public static SqlSession openSession() {
        SqlSession session = sqlSessionFactory.openSession();
        return session;
    }

    /**
     * 配套手动提交事务使用: 成功提交
     *
     * @param session 传入连接对象
     */
    public static void commitAndClose(SqlSession session) {
        session.commit();
        session.close();
    }

    /**
     * 配套手动提交事务使用: 失败回滚
     *
     * @param session 传入连接对象
     */
    public static void rollbackAndClose(SqlSession session) {
        session.rollback();
        session.close();
    }
}
