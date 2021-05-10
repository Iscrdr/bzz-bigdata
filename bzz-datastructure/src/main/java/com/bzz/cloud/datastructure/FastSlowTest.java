package com.bzz.cloud.datastructure;



/**
 * @author ：Iscrdr
 * @description：链表快慢指针问题
 * @email ：624003618@qq.com
 * @date ：2021-04-06 06:17
 * @modified By：
 * @version: 1.0.0
 */
public class FastSlowTest {
    private static class Node<T>{
        T item;
        Node next;
        public Node(T item, Node next){
            this.item = item;
            this.next = next;
        }

    }

    /**
     * 获取单项链表的中间节点
     * @param node
     * @return
     */
    public static Node getMid(Node node){

        int i=0;
        Node fastNode = node;
        Node slowNode = node;

        while (fastNode!=null && fastNode.next!=null){
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
            i++;
        }
        return slowNode;
    }

    /**
     * 判断单向链表是否有环问题
     * @param node
     * @return
     */
    public static boolean isCircle(Node node){

        Node fastNode = node;
        Node slowNode = node;

        while (fastNode!=null && fastNode.next!=null){
            fastNode = fastNode.next.next;
            slowNode = slowNode.next;
            if(fastNode.item.equals(slowNode.item)){
               return true;
            }
        }
        return false;
    }

    /**
     * 找到有环单向链表的入口
     * @param node
     * @return
     */
    public static Node getEntiesNode(Node node){

        //快指针
        Node fastNode = node;
        //慢指针
        Node slowNode = node;
        //临时指针
        Node tempNode = null;
        while (fastNode!=null && fastNode.next!=null){
            //快指针,每次走两步
            fastNode = fastNode.next.next;
            //慢指针,每次走一步
            slowNode = slowNode.next;
            /*
             * 慢指针与快指针相遇
             */
            if(fastNode.item.equals(slowNode.item)){
                //临时指针从起点开始走,每次走1步
                if(tempNode ==null){
                    tempNode = node;
                }else {
                    tempNode = tempNode.next;
                }
            }
            /*
             * 慢指针与临时指针相遇,返回环入点
             */
            if(tempNode !=null && tempNode.next!=null){
                if(slowNode.item.equals(tempNode.item)){
                    return tempNode;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Node<String> first = new Node<>("aa",null);
        Node<String> second = new Node<>("bb",null);
        Node<String> third = new Node<>("cc",null);
        Node<String> four = new Node<>("dd",null);
        Node<String> five = new Node<>("ee",null);
        Node<String> six = new Node<>("ff",null);
        Node<String> seven = new Node<>("gg",null);

        first.next = second;
        second.next = third;
        third.next = four;
        four.next = five;
        five.next = six;
        six.next = seven;

        Node mid = getMid(first);
        System.out.println(mid.item);

        //有环问题
        seven.next = five;

        //boolean flag = isCircle(first);
        //System.out.println(flag);

        Node entiesNode = getEntiesNode(first);
        System.out.println("有环的入口节点："+entiesNode.item);

    }
}
