package com.itheima.dao;

import com.itheima.domain.QueryVo;
import com.itheima.domain.User;

import java.util.List;

public interface UserDao {
    /**
     * 查询所有
     * @return
     */
    List<User> findAll();



    User findById(Integer userId);


    List<User> findByName(String username);


    List<User> findByVo(QueryVo vo);


    List<User> findByCondition(User user);


    List<User> findByIds(QueryVo vo);
}
