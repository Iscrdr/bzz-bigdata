package com.bzz.cloud.datastructure.linklist;

/**
 * @author ：Iscrdr
 * @description：链表
 * @email ：624003618@qq.com
 * @date ：2021-03-10 22:45
 * @modified By：
 * @version: 1.0.0
 */
public class LinkList<T> {

    //记录头节点
    private Node head;

    //记录链表的长度
    private int N;


    private class Node{
        T item;
        Node next;
        public Node(T item,Node next){
            this.item = item;
            this.next = next;
        }

    }

    public LinkList(){
        this.head = new Node(null,null);
        this.N = 0;
    }


    /**
     * @params：
     * @return：
     * @desc：清空链表
     */
    public void clear(){
        head.next = null;
        this.N = 0;
    }
    //获取链表的长度

    public int length(){
        return N;
    }
    //判断链表是否为空
    public boolean isEmpty(){
        return N==0;
    }
    //获取指定位置i的元素
    public T get(int offset){

        Node n = head.next;
        for(int i=1;i<offset;i++){
            n = n.next;
        }

        return n.item;
    }
    //向链表中添加元素
    public void insert(T t){
        Node newNode = new Node(t,null);
        Node n = head;
        while (n.next != null){
            n = n.next;
        }
        n.next = newNode;
        N++;
    }
    //向链表中指定位置i添加元素
    public void insert(int i,T t){
        Node pre = head;

        for(int index=1;index<=i;index++){
            pre = pre.next;
        }
        Node third = pre.next;

        Node newNode = new Node(t,null);
        pre.next = newNode;

        newNode.next = third;
        N++;


    }
    //移除指定位置i处的元素,并返回改元素
    public T remove(int i){
        Node node = head;
        Node first = null;
        if(head.next == null ){
            return null;
        }
        for(int index=0;index<=i;index++){
            first = node;
            node = node.next;
        }

        first.next = node.next;
        N--;

        return node.item;

    }
    //查找元素t在链表中第一次出现的位置
    public int indexOf(T t){
        Node first = head.next;
        int i=1;
        while (first != null){
            if(first.item.equals(t)){
                i++;
                return i;
            }
            first = first.next;
        }
        return -1;
    }
    /**
     * 反转整个链表
     */
    public void reverse(){
        if(isEmpty()){
            return;
        }
        reverse(head.next);
    }

    /**
     * 反转指定某个节点链表
     */
    public Node reverse(Node curr){
        if(curr.next == null){
            head.next = curr;
            return curr;
        }

        //递归反转当前节点的下一个节点,返回值就是反转后当前节点的上一个节点
        Node pre = reverse(curr.next);
        //让返回的节点的下一个节点变为currentNode
        pre.next = curr;
        //把当前节点的下一个节点变为null
        curr.next = null;
        return curr;
    }


    public void print(){
        Node first = head.next;
        System.out.println("头节点head:"+head.item);
        int i=1;
        while (first.next != null){
            System.out.println("第"+i+"节点："+first.item);
            first = first.next;
            i++;
        }
        //打印最后一个节点
        System.out.println("第"+i+"节点："+first.item);
    }

}
