package com.bzz.cloud;


import com.bzz.cloud.datastructure.Queue;
import com.bzz.cloud.datastructure.tree.BinaryTree;
import org.junit.Test;


/**
 * @author ：Iscrdr
 * @description：二叉树测试
 * @email ：624003618@qq.com
 * @date ：2021-05-10 02:46
 * @modified By：
 * @version: 1.0.0
 */
public class BinaryTreeTest {



    @Test
    public void testBinTree(){
        BinaryTree<Integer,String> bt = new BinaryTree();
        bt.put(1,"李四");
        bt.put(2,"张三");
        bt.put(3,"王六");
        bt.put(4,"蛮王");
        bt.put(5,"唐三");
        bt.put(7,"小舞");

        System.out.println("插入后的个数："+bt.size());

        System.out.println("key为2对应的value: "+bt.get(2));
//        bt.delete(3);
//        System.out.println("delete删除后的个数："+bt.size());

        Integer min = bt.min();
        System.out.println("最小的键："+min);
        Integer max = bt.max();
        System.out.println("最大的键："+max);

    }
    @Test
    public void testPreErgodic(){
        BinaryTree<String,Integer> bt = new BinaryTree();


        bt.put("E",5);
        bt.put("B",2);
        bt.put("G",7);
        bt.put("A",1);
        bt.put("D",4);
        bt.put("F",6);
        bt.put("H",8);
        bt.put("C",3);

        //前序遍历
        Queue<String> keys1 = bt.preErgodic();

        for(Object key : keys1){
            System.out.println(key+":"+bt.get((String) key));
        }
        System.out.println("========================================");
        //中序遍历
        Queue<String> keys2 = bt.midErgodic();
        for(Object key : keys2){
            System.out.println(key+":"+bt.get((String) key));
        }
        System.out.println("========================================");
        //后序遍历
        Queue<String> keys3 = bt.afterErgodic();
        for(Object key : keys3){
            System.out.println(key+":"+bt.get((String) key));
        }
    }

    @Test
    public void testLayerErgodic(){
        BinaryTree<String,Integer> bt = new BinaryTree();


        bt.put("E",5);
        bt.put("B",2);
        bt.put("G",7);
        bt.put("A",1);
        bt.put("D",4);
        bt.put("F",6);
        bt.put("H",8);
        bt.put("C",3);

        Queue<String> keys = bt.layerErgodic();

        for(Object key : keys){
            System.out.println(key+":"+bt.get((String) key));
        }
    }

    @Test
    public void testMaxDepth(){
        BinaryTree<String,Integer> bt = new BinaryTree();


        bt.put("E",5);
        bt.put("B",2);
        bt.put("G",7);
        bt.put("A",1);
        bt.put("D",4);
        bt.put("F",6);
        bt.put("H",8);
        bt.put("C",3);

        int i = bt.maxDepth();
        System.out.println("最大深度："+i);
    }
}
