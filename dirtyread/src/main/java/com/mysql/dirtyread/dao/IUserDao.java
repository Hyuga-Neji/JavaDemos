package com.mysql.dirtyread.dao;

import com.mysql.dirtyread.entity.User;

public interface IUserDao {

    /**
     * 根据名字查询 User
     * @param name 名字
     * @return User 对象
     */
    User getUserByName(String name);

    /**
     * 新增 User
     * @param user User 对象
     * @return 音响条数
     */
    int addUser(User user);
}
