package com.qf.ran.service;

import com.qf.ran.dao.UserDao;
import com.qf.ran.entity.User;

/**
 * @author Ran
 * @since JDK 1.8
 */
public class UserService {
    private UserDao userDao = new UserDao();

    //注册功能
    public boolean register(User user){
        //1.判断用户名是否存在
        boolean flag = userDao.checkUsername(user.getUsername());
        if(flag){
            return false;
        }
        //2.添加用户信息
        userDao.addUser(user);
        return true;
    }

    //登录功能
    public boolean login(String username, String password) {
        //1.判断用户名是否存在
        if(userDao.checkUsername(username)){
            User user = new User(username, password);
            //2.判断用户名和密码是否都正确
            if(userDao.checkUser(user)){
                return true;
            }
        }
        return false;
    }
}
