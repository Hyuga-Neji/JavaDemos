package com.mysql.dirtyread.dao.impl;

import com.mysql.dirtyread.dao.IUserDao;
import com.mysql.dirtyread.entity.User;
import com.mysql.dirtyread.mapper.UserMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserDaoImpl implements IUserDao {

    @Resource
    private UserMapper userMapper;

    /**
     * 根据名字查询 User
     *
     * @param name 名字
     * @return User 对象
     */
    @Override
    public User getUserByName(String name) {
        return userMapper.selectUserByName(name);
    }

    /**
     * 新增 User
     *
     * @param user User 对象
     * @return 音响条数
     */
    @Override
    public int addUser(User user) {
        return userMapper.insertUser(user);
    }
}
