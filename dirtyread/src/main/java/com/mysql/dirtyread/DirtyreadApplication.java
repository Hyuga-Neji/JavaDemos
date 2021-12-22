package com.mysql.dirtyread;

import com.mysql.dirtyread.entity.User;
import com.mysql.dirtyread.service.IUserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class DirtyreadApplication implements ApplicationRunner {

	@Resource
	private IUserService userService;

	public static void main(String[] args) {
		SpringApplication.run(DirtyreadApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		for (int i = 0; i < 15; i++) {
            User user = new User("张三", "123", 15);
            try {
                new Thread(() -> userService.addUser(user)).start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
	}
}
