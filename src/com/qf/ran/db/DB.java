package com.qf.ran.db;

import com.qf.ran.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ran
 * @since JDK 1.8
 */
//数据库
public class DB {
    public static List<User> list = new ArrayList();
    //public static String serverCode;
    static{
        list.add(new User("zs","123","man",new String[]{"eat"},"guangdong"));
        list.add(new User("ls","456","women",new String[]{"eat","drink"},"guangxi"));
        list.add(new User("admin","admin","man",new String[]{"eat","drink","play"},"fujian"));
    }
}
