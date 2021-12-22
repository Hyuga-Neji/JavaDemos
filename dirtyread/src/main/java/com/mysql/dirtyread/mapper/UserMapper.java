package com.mysql.dirtyread.mapper;

import com.mysql.dirtyread.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    /**
     * 根据名字查询 User
     * @param name 名字
     * @return User 对象
     */
    @Select("SELECT * FROM `user` WHERE username = #{name}")
    @Results(id = "userResultMap", value = {
            @Result(property = "userName", column = "username")
    })
    User selectUserByName(String name);

    /**
     * 新增 User
     * @param user User 对象
     * @return 音响条数
     */
    @Insert("INSERT INTO `user` (username, pwd, age) VALUES (#{userName}, #{pwd}, #{age})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(User user);

}
