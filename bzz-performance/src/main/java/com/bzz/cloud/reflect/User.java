package com.bzz.cloud.reflect;

import java.util.Date;

/**
 * @author ：Iscrdr
 * @description：测试domain
 * @email ：624003618@qq.com
 * @date ：2021-01-25 11:08
 * @modified By：
 * @version: 1.0.0
 */
public class User {
    private String name;
    private int age;
    private Date birthDay;

    public String sayHi(){
        return "hi";
    }


    public User() {
    }

    public User(String name, int age, Date birthDay) {
        this.name = name;
        this.age = age;
        this.birthDay = birthDay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
}
