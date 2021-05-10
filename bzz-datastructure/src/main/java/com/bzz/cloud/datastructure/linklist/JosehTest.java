package com.bzz.cloud.datastructure.linklist;

/**
 * @author ：Iscrdr
 * @description：约瑟夫问题
 * @email ：624003618@qq.com
 * @date ：2021-04-06 09:20
 * @modified By：
 * @version: 1.0.0
 */
public class JosehTest {
    private static class Node<T>{
        T item;
        Node next;
        public Node(T item, Node next){
            this.item = item;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        Node<Integer> first = null;
        Node<Integer> pre = null;
        for(int i=1;i<= 41;i++){
            //第一个节点
            if(i==1){
                //第一次初始化节点
                first = new Node<>(i,null);
                pre = first;
                continue;
            }
            //如果不是第一个节点
            Node<Integer> newNode = new Node(i, null);
            pre.next =newNode;
            pre = newNode;

            //如果是最后一个节点,那么需要让最后一个节点的下一个节点指向第一个节点,形成循环链表
            if(i==41){
                pre.next = first;
            }
        }
        //2.需要count计数器,模拟报数
        int count = 0;
        //3.遍历循环链表,纪录每次拿到的节点,默认从首节点开始
        Node<Integer> n = first;
        //记录当前节点的前一个节点
        Node<Integer> before = null;

        while (n!=n.next){
            //摸你报数
            count++;
            if(count==3){
                before.next = n.next;
                System.out.print(n.item+",");
                count = 0;
            }else {
                before = n;
            }
            n = n.next;
        }
        System.out.println(n.item);

    }
}
