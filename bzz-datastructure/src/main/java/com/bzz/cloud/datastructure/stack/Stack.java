package com.bzz.cloud.datastructure.stack;

import java.util.Iterator;

/**
 * @author ：Iscrdr
 * @description：栈
 * @email ：624003618@qq.com
 * @date ：2021-04-19 01:46
 * @modified By：
 * @version: 1.0.0
 */
public class Stack<T> implements Iterable{

    private Node head;

    private int N;



    private class Node{
        public T item;
        public Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public Stack(){
        this.head = new Node(null,null);
        this.N = 0;
    }
    public boolean isEmpty(){
        return N==0;
    }
    public int size(){
        return N;
    }

    /**
     * 压栈,把元素压入栈中
     * @param t
     */
    public void push(T t){
        //找到首节点指向的第一个节点
        Node oldFirst = head.next;

        //创建新节点
        Node newNode = new Node(t,null);
        //让首节点指向新节点
        head.next = newNode;
        //让新节点指向原来的第一个节点
        newNode.next = oldFirst;
        //元素个数加1;
        N++;
    }

    public T pop() {
        Node next = head.next;
        if(null == next){
            return null;
        }
        //找到第一个节点
        T item = head.next.item;
        //让首节点的第一个节点指向原来第一个节点的下一个节点
        head.next = next.next;
        //元素个数减1
        N--;
        return next.item;
    }

    @Override
    public Iterator iterator() {
        return new Siterator();
    }
    private class Siterator implements Iterator{
        private Node n;
        public Siterator(){
            this.n = head;
        }

        @Override
        public boolean hasNext() {
            return n.next != null;
        }

        @Override
        public Object next() {
            n = n.next;
            return n.item;
        }
    }
}
