package com.mysql.dirtyread.service.impl;

import com.mysql.dirtyread.dao.IUserDao;
import com.mysql.dirtyread.dao.impl.UserDaoImpl;
import com.mysql.dirtyread.entity.User;
import com.mysql.dirtyread.mapper.UserMapper;
import com.mysql.dirtyread.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private IUserDao userDao;

    /**
     * 新增 User
     *
     * @param user User 对象
     * @return 音响条数
     */
    @Override
    public synchronized int addUser(User user){
        try {
            User dbUser = userDao.getUserByName(user.getUserName());
            System.out.println(dbUser == null);
            Thread.sleep(2000);
            if(dbUser == null){
                return userDao.addUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) throws InterruptedException {

    }
}
