package com.bzz.cloud.flink.api.dataset;

/**
 * @program: bzz-bigdata
 * @description: POJO
 * @author: 624003618@qq.com
 * @create: 2019-08-17 04:01
 */
public class Person2 {
    private int id;
    private String name;
    private int age;

    public Person2(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Person2() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Person2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
