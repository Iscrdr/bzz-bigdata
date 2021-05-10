package com.bzz.cloud;

import com.bzz.cloud.datastructure.Queue;
import com.bzz.cloud.datastructure.stack.Stack;
import org.junit.Test;

/**
 * @author ：Iscrdr
 * @description：队列测试
 * @email ：624003618@qq.com
 * @date ：2021-04-19 04:00
 * @modified By：
 * @version: 1.0.0
 */
public class QueueTest {

    @Test
    public void pushStack(){
        Queue<String> queue = new Queue();

        queue.enqueue("aa");
        queue.enqueue("bb");
        queue.enqueue("cc");
        queue.enqueue("dd");

        for(Object item : queue){
            System.out.println(item);
        }

        String que = queue.dequeue();
        System.out.println("弹出的元素："+que);
        System.out.println("剩余元素个数："+queue.size());
    }
}
