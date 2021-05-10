package com.bzz.cloud;


import org.junit.Test;

/**
 * @author ：Iscrdr
 * @description：链表面试题
 * @email ：624003618@qq.com
 * @date ：2021-04-07 02:57
 * @modified By：
 * @version: 1.0.0
 */
public class LinkListTest {


    /**
     * 　　1、单链表的创建和遍历
     *
     * 　　2、求单链表中节点的个数
     *
     * 　　3、查找单链表中的倒数第k个结点（剑指offer，题15）
     *
     * 　　4、查找单链表中的中间结点
     *
     * 　　5、合并两个有序的单链表，合并之后的链表依然有序【出现频率高】（剑指offer，题17）
     *
     * 　　6、单链表的反转【出现频率最高】（剑指offer，题16）
     *
     * 　　7、从尾到头打印单链表（剑指offer，题5）
     *
     * 　　8、判断单链表是否有环
     *
     * 　　9、取出有环链表中，环的长度
     *
     * 　　10、单链表中，取出环的起始点（剑指offer，题56）。本题需利用上面的第8题和第9题。
     *
     * 　　11、判断两个单链表相交的第一个交点（剑指offer，题37）
     */


    /**
     * 7、从尾到头打印单向链表
     */
    @Test
    public void reversePrintLinkList(){
        Node<String> one = new Node("aa",null);
        Node<String> two = new Node("bb",null);
        Node<String> third = new Node("cc",null);
        Node<String> four = new Node("dd",null);
        Node<String> five = new Node("ee",null);
        one.next = two;
        two.next = third;
        third.next = four;
        four.next = five;

        Node<String> temp = one;
        int count = 0;
        //遍历链表
        /*while (temp!=null){
            count++;
            System.out.println(temp.data);
            temp = temp.next;
        }
        System.out.println("链表中节点的个数："+count);
        System.out.println("===============从尾逆序打印链表================");
*/
        //printLinkList(one);
        //Node offset = getOffset(one, 4);
        Node midNode   = getMid(one);
        System.out.println(midNode.data);

    }

    /**
     * 3、查找单向链表倒数第K个节点
     * 解决方案：
     * a,采用两遍或多遍扫描链表，先得到链表长度，再顺序扫描到L − K L-KL−K位置；
     * b,采用递归算法，在递归返回位置计数，计到k kk位置；
     * c,辅助数组；
     * 以上都不是最高效算法,
     * 可以采用两个指针p1,p2. p1和p2相隔K个点,p1从起点出遍历,p2从第K个点遍历,当p2走到最后一个节点时,那么p1就是倒数第K个节点
     *
     */
    public Node getOffset(Node first,int k){

        Node kNode = first;
        int count = 0;
        while (first!=null){
            //第一步找到第K个节点
            if(count >= k){
                //第二个指针开始走
                kNode = kNode.next;
            }
            first = first.next;
            count++;
        }
        return kNode;
    }


    /**
     * 4、查找单向链表中间点
     * 解决方案：
     * 用两个指针p1,p2 第一个走1步,第二个走两步. 第二个走完时,第一个就是中间点
     *
     */
    public Node getMid(Node first){

        Node midNode = first;
        while (first!=null ){
            if(first.next == null){
                break;
            }
            first = first.next.next;
            midNode = midNode.next;
        }
        return midNode;
    }
    /**
     * 4、合并两个有序链表
     * 1,3,5,7
     * 2,6,8,9
     */
    public Node getMid(Node<Integer> nodeA,Node<Integer> nodeB){

        while (nodeA!=null){
            if(nodeA.data < nodeB.data){
                nodeA.next = nodeB;
                nodeB.next = nodeA.next.next;
            }else {
                nodeB.next = nodeA;
            }
            nodeA = nodeA.next;
        }



        return nodeA;
    }

    /**
     * 7、从尾到头打印单向链表
     */
    public void printLinkList(Node first){
        if (first == null){
            return;
        }
        printLinkList(first.next);
        System.out.println(first.data);
    }

    public class Node<T> {
        private T data;
        private Node next;
        public Node(T data, Node next){
            this.data = data;
            this.next = next;
        }
    }


}
