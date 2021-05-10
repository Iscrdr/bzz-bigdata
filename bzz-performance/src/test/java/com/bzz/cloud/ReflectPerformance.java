package com.bzz.cloud;

import com.bzz.cloud.reflect.User;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author ：Iscrdr
 * @description：反射性能测试
 * @email ：624003618@qq.com
 * @date ：2021-01-25 11:06
 * @modified By：
 * @version: 1.0.0
 */
public class ReflectPerformance {

    @Test
    public void testCreate1() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        for(int n = 0;n<10;n++){
            long start = System.currentTimeMillis();
            User user = null;
            int i = 0;
            while(i<10000000){
                ++i;
                user = new User();
            }
            long end = System.currentTimeMillis();
            System.out.println("普通对象创建耗时："+(end - start ) + "ms");
        }


    }
    @Test
    public void testCreate2() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        for(int n = 0;n<10;n++) {
            long start = System.currentTimeMillis();
            User user1 = null;
            int j = 0;
            while (j < 10000000) {
                ++j;
                user1 = (User) Class.forName("com.bzz.cloud.reflect.User").newInstance();
            }
            long end = System.currentTimeMillis();
            System.out.println("无缓存反射创建对象耗时：" + (end - start) + "ms");
        }

    }
    @Test
    public void testCreate3() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        for(int n = 0;n<10;n++) {
            long start = System.currentTimeMillis();

            User user = null;
            Class rUserClass = Class.forName("com.bzz.cloud.reflect.User");
            int k = 0;
            while (k < 10000000) {
                ++k;
                user = (User) rUserClass.newInstance();
            }
            long end = System.currentTimeMillis();
            System.out.println("通过缓存反射创建对象耗时：" + (end - start) + "ms");

        }
    }

    @Test
    public void testReflexMethod() throws Exception {
        long start = System.currentTimeMillis();
        Class testUserClass = Class.forName("com.bzz.cloud.reflect.User");
        User testUser = (User) testUserClass.newInstance();
        Method method = testUserClass.getMethod("sayHi");
        int i = 0;
        while(i<100000000){
            ++i;
            method.invoke(testUser);
        }
        long end = System.currentTimeMillis();
        System.out.println("反射调用方法耗时："+(end - start ) + "ms");
    }

    @Test
    public void testReflexMethod2() throws Exception {
        long start = System.currentTimeMillis();
        Class testUserClass = Class.forName("com.bzz.cloud.reflect.User");
        User testUser = (User) testUserClass.newInstance();
        Method method = testUserClass.getMethod("sayHi");
        int i = 0;
        while(i<100000000){
            ++i;
            method.setAccessible(true);
            method.invoke(testUser);
        }
        long end = System.currentTimeMillis();
        System.out.println("setAccessible=true 反射调用方法耗时："+(end - start ) + "ms");
    }

}
