package com.mysql.dirtyread.service;

import com.mysql.dirtyread.entity.User;

public interface IUserService {

    /**
     * 新增 User
     * @param user User 对象
     * @return 音响条数
     */
    int addUser(User user);
}
