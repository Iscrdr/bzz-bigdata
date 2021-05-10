package com.bzz.cloud.datastructure.linklist;

import java.util.Iterator;

/**
 * @author ：Iscrdr
 * @description：线性表
 * @email ：624003618@qq.com
 * @date ：2021-02-21 00:50
 * @modified By：
 * @version: 1.0.0
 */
public class SequenceList<T> implements Iterable<T> {

    //存储元素的数组
    private T[] eles;

    //记录当前顺序表中元素的个数;
    private int N;

    //构造方法
    public SequenceList(int capacity) {
        //初始化数组
        this.eles = (T[]) new Object[capacity];
        //初始化长度
        this.N = 0;
    }
    //将一个线性表置为空表
    public void clear(){
        this.N = 0;
    }
    //判断当前线性表是否为空表
    public boolean isEmpty(){
        return N==0;
    }
    //获取线性表的长度
    public int length(){
        return N;
    }
    //获取指定位置的元素
    public T get(int i){
        return eles[i];
    }
    //向线性表中插入元素
    public void insert(T t){
        if(N==eles.length){
            resize(2*eles.length);
        }


        eles[N++] = t;
    }
    //向线性表中指定的索引插入元素
    public void insert(int i,T t){
        if(N==eles.length){
            resize(2*eles.length);
        }
        //先把i索引处的元素及其后面的元素依次向后移动一位
        for(int index=N;index > i;index--){
            eles[index] = eles[index-1];
        }
        //在把t元素返回到i索引处即可
        eles[i] = t;
        N++;

    }
    //删除指定位置的元素,并返回该元素
    public T remove(int i){
        //
        T current = eles[i];

        //先把i索引处的元素及其后面的元素依次向后移动一位
        for(int index=i;index < N - 1;index++){
            eles[index] = eles[index+1];
        }
        //元素个数减1
        N--;

        if(N<eles.length/4){
            resize(eles.length/2);
        }

       return current;
    }

    public int indexOf(T t){
        for(int i=0;i<N;i++){
            if(eles[i].equals(t)){
                return i;
            }

        }
        return -1;
    }

    //扩容,根据newsize,重置else的大小
    public void resize(int  size){
        //定义一个临时数组,指向原数组
        T[] temp = eles;

        //创建新数组
        eles = (T[]) new Object[size];
        //把原数组的数据拷贝到新数组即可
        for(int i=0;i<N;i++){
            eles[i] = temp[i];
        }

    }




    @Override
    public Iterator<T> iterator() {
        return new SIterator();
    }

    private class SIterator implements Iterator<T>{

        private int cusor;
        public SIterator(){
            cusor = 0;
        }

        @Override
        public boolean hasNext() {
            return cusor < N;
        }

        @Override
        public T next() {
            return eles[cusor++];
        }


    }
}
