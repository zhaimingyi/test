package com.itheima.test;

import com.itheima.dao.UserDao;
import com.itheima.domain.QueryVo;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MybatisTest {
    private InputStream in;
    private SqlSession sqlSession;
    private UserDao userDao;

    @Before//用于在测试方法执行之前执行
    public void init()throws Exception{
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession();
        //4.获取dao的代理对象
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @After//用于在测试方法执行之后执行
    public void destroy()throws Exception{
        //提交事务
        sqlSession.commit();
        //6.释放资源
        sqlSession.close();
        in.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll(){
        //5.执行查询所有方法
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }

    }



    @Test
    public void testFindOne() {
        User user = userDao.findById(50);
        System.out.println(user);
    }


    @Test
    public void testFindName() {
        List<User> users = userDao.findByName("%王%");
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void testFindByVo() {
        QueryVo vo = new QueryVo();
        User u = new User();
        u.setUsername("%王%");
        vo.setUser(u);
        List<User> users = userDao.findByVo(vo);
        for (User user : users) {
            System.out.println(user);
        }

    }


    @Test
    public void testFindByCondition() {
        User u = new User();
        u.setUsername("老王");
        u.setSex("女");
        List<User> users = userDao.findByCondition(u);
        for (User user : users) {
            System.out.println(user);
        }

    }


    @Test
    public void testFindByIds() {
        QueryVo vo = new QueryVo();
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(41);
        list.add(42);
        list.add(43);
        list.add(46);
        vo.setIds(list);

        List<User> users = userDao.findByIds(vo);
        for (User user : users) {
            System.out.println(user);
        }

    }

}
