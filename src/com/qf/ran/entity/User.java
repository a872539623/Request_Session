package com.qf.ran.entity;

import java.util.Arrays;

/**
 * @author Ran
 * @since JDK 1.8
 */
public class User {
    private String username;
    private String password;
    private String sex;
    private String[] hobbies;
    private String addrs;

    public User(String username, String password, String sex, String[] hobbies, String addrs) {
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.hobbies = hobbies;
        this.addrs = addrs;
    }
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    public String getAddrs() {
        return addrs;
    }

    public void setAddrs(String addrs) {
        this.addrs = addrs;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", hobbies=" + Arrays.toString(hobbies) +
                ", addrs='" + addrs + '\'' +
                '}';
    }
}
