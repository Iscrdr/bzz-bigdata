package com.bzz.cloud.datastructure;

import java.util.Iterator;

/**
 * @author ：Iscrdr
 * @description：队列
 * @email ：624003618@qq.com
 * @date ：2021-04-19 03:39
 * @modified By：
 * @version: 1.0.0
 */
public class Queue<T> implements Iterable {

    /**
     * 纪录首节点
     */
    private Node head;

    private int N;

    /**
     * 纪录最后一个节点
     */
    private Node last;

    public Queue(){
        this.head = new Node(null,null);
        this.N = 0;
        this.last = null;
    }



    private class Node{
        private T item;
        private Node next;
        public Node(T item,Node next){
            this.item = item;
            this.next = next;
        }
    }

    public boolean isEmpty(){
        return  N == 0;
    }

    public int size(){
        return N;
    }

    /**
     * 往队列中插入一个元素
     * @param t
     */
    public void enqueue(T t){

        if(last == null){
            last = new Node(t, null);
            head.next = last;
        }else {
            //新建一个节点
            Node newNode = new Node(t, null);
            //尾节点指向下一个节点
            last.next = newNode;
            //让新节点成为last节点
            last = newNode;
        }
        N++;

    }

    /**
     * 从队列中拿出一个元素
     * @param
     */
    public T dequeue(){
        if(isEmpty()){
            return null;
        }

        //拿出第一个元素
        Node oldFirst = head.next;

        //首节点的下一个元素指向第一个节点的下一个元素
        head.next = oldFirst.next;
        N--;
        //出队列其实是在删除元素,因此如果队列中的元素被删除完了,需要重置last为null
        if(isEmpty()){
            last = null;
        }


        return  oldFirst.item;
    }

    @Override
    public Iterator iterator() {
        return new QueueIterator();
    }

    class QueueIterator implements Iterator{

        private Node n;
        public QueueIterator(){
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
