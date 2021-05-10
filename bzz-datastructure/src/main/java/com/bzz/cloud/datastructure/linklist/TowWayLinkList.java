package com.bzz.cloud.datastructure.linklist;

/**
 * @author ：Iscrdr
 * @description：双向链表
 * @email ：624003618@qq.com
 * @date ：2021-04-06 03:21
 * @modified By：
 * @version: 1.0.0
 */
public class TowWayLinkList<T> {

    //记录首节点
    private Node head;

    //记录尾节点
    private Node last;

    //记录链表的长度
    private int N;



    private class Node{
        T data;
        Node pre;
        Node next;

        public Node(T data, Node pre, Node next){
            this.pre = pre;
            this.data = data;
            this.next = next;
        }

    }

    public TowWayLinkList(){
        this.head = new Node(null,null,null);
        this.last = null;
        this.N = 0;

    }

    /**
     * 获取双向链表的长度
     * @return
     */
    public int length(){
        return N;
    }
    /**
     * 判断双向链表是否为空,是返回true,否返回false
     */
    public boolean isEmpty(){

        return N<=0;
    }

    /**
     * @params：
     * @return：
     * @desc：清空链表
     */
    public void clear(){
        this.head.next = null;
        this.head.pre = null;
        this.last = null;
        this.N = 0;
    }
    /**
     * 获取第一个元素
     */
    public T getFirst(){
        if (isEmpty()){
            return null;
        }
        return this.head.next.data;
    }
    /**
     * 获取最后一个元素
     */
    public T getLast(){
        if (isEmpty()){
            return null;
        }
        return this.last.data;
    }

    /**
     * 获取指定位置i的元素
     * @param i
     * @return
     */
    public T get(int i){

        Node current = this.head.next;
        for(int index=0;index<i-1;index++){
            current = current.next;
        }
        return current.data;

    }
    /**
     * 向链表中添加元素
     */
    public void insert(T t){

        if(isEmpty()){
            //如果链表为null
            //1,创建新节点,新结点的上一个节点为头节点,下一个节点为null
            Node node = new Node(t, head, null);
            //2,让新节点成为尾节点
            this.last = node;
            //3,让头节点指向尾节点,由于链表是双向的,尾结点的上一个节点为头节点
            head.next = last;

        }else {
            //如果链表不为null
            Node oldLast = this.last;
            //1,创建新节点,新结点的上一个节点为尾节点,下一个节点为null
            Node newList = new Node(t, oldLast, null);
            //2,让当前尾节点指向新节点
            oldLast.next = newList;
            //3,让新节点成为尾节点
            last = newList;
        }

        N++;
    }

    /**
     * 在指定位置处插入元素
     * @param i
     * @param t
     */
    public void insert(int i,T t){
        if(isEmpty() || length() < i){
            //如果链表为null
            //1,创建新节点,新结点的上一个节点为头节点,下一个节点为null
            Node node = new Node(t, head, null);
            //2,让新节点成为尾节点
            this.last = node;
            //3,让头节点指向尾节点,由于链表是双向的,尾结点的上一个节点为头节点
            head.next = last;
        }else {
            //上一个节点
            Node pre = this.head;
            for(int index=0;index<i-1;index++){
                pre = pre.next;
            }
            //下一个节点
            Node next = pre.next;
            //新节点的前一个节点指向pre节点,新节点的下一个节点指向pre的下一个节点
            Node newNode = new Node(t, pre, next);
            //前一个节点的下一个节点指向新节点
            pre.next = newNode;
            //下一个节点的前一个节点指向新节点
            next.pre = newNode;
        }
        N++;

    }
    /**
     * 移除指定位置i处的元素,并返回改元素
     * @param i
     *
     */
    public T remove(int i){
        if(isEmpty() || length() < i){
            return null;
        }else {
            //上一个节点
            Node current = this.head;
            for(int index=0;index<i;index++){
                current = current.next;
            }
            //前一个节点
            Node pre = current.pre;

            //下一个节点
            Node next = current.next;

            //当前节点的前一个节点指向当前节点的下一个节点
            pre.next = next;
            //当前节点的下一个节点指向当前节点的前一个节点
            next.pre = pre;
            N--;
            return current.data;
        }

    }


    /**
     * 查找元素t在链表中第一次出现的位置
     * @param t
     * @return
     */
    public int indexOf(T t){
        if(isEmpty()){
           return -1;
        }else {
            //上一个节点
            Node node = this.head;
            int i=0;
            while (node.next!=null){
                node = node.next;
                if(node.data.equals(t)){
                    i++;
                    return i;
                }
            }
            return -1;
        }

    }



    public void print(){
        Node node = head;
        System.out.println("头节点："+head.data);
        int i=0;
        while (node.next != null){
            i++;
            node = node.next;
            if(node.next!=null){
                System.out.println("前一个节点："+node.pre.data+",第"+i+"节点："+node.data+",下一个节点："+node.next.data);
            }else {
                System.out.println("前一个节点："+node.pre.data+",第"+i+"节点："+node.data+",下一个节点：null");
            }
        }

    }


}
