package com.qf.ran.dao;

import com.qf.ran.db.DB;
import com.qf.ran.entity.User;

/**
 * @author Ran
 * @since JDK 1.8
 */
public class UserDao {
    //判断用户名是否存在
    public boolean checkUsername(String username){
        for (User user:DB.list) {
            if(user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    //添加用户信息
    public void addUser(User user){
        DB.list.add(user);
    }

    //检查用户名和密码是否都正确
    public boolean checkUser(User user){
        for (User u:DB.list) {
            if(u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }
}
