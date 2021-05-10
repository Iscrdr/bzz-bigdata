package com.bzz.cloud.datastructure.tree;


import com.bzz.cloud.datastructure.Queue;

/**
 * @author ：Iscrdr
 * @description：二叉树
 * @email ：624003618@qq.com
 * @date ：2021-04-28 04:02
 * @modified By：
 * @version: 1.0.0
 */
public class BinaryTree <Key extends Comparable<Key>,Value>{

    /**
     * 根节点
     */
    private Node root;

    private int N;

    class Node{

        /**
         * 存储键
         */
        public Key key;
        /**
         * 存储值
         */
        private Value value;

        /**
         * 左节点
         */
        public Node left;
        /**
         * 右节点
         */
        public Node right;

        public Node(Key key,Value value,Node left,Node right){
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 获取元素的个数
     * @return
     */
    public int size(){
        return N;
    }

    /**
     * 插入一个元素
     * @return
     */
    public void put(Key key,Value value){

       root =  put(root,key,value);
    }
    /**
     * 插入一个元素
     * @return
     */
    public Node put(Node x,Key key,Value value){

        //如果x子树为空
        if(x == null){
            return new Node(key,value,null,null);
        }

        /*
         *如果x子树不为空,比较x结点的键和key的大小
         * 如果key比x节点的键小,则查找x的左子树,
         * 如果key比x节点的键大,则查找x的右子树,
         * 如果key等于x节点的键,则将x节点的值替换为value
         */
        int cmp = key.compareTo(x.key);
        if(cmp>0){

            //如果key比x节点的键大,则查找x的右子树,
            x.right =   put(x.right,key,value);
        }else if (cmp<0){
            x.left =  put(x.left,key,value);
        }else if (cmp == 0){
            x.value = value;
        }
        N++;
        return x;


    }

    /**
     * 查询树中指定key对应的value的值
     */
    public Value get(Key key){
        return get(root,key);
    }

    /**
     * 查询指定树中指定key对应的value的值
     */
    public Value get(Node x,Key key){
        //x树为null
        if(x==null){
            return null;
        }

        /*
         * x树不为null
         */
        int cmp = key.compareTo(x.key);
        if(cmp>0){
            //如果key比x节点的键大,则查找x的右子树
            return get(x.right,key);
        }else if (cmp<0){
            //如果key比x节点的键小,则查找x的左子树
            return get(x.left,key);
        }else {
            return x.value;
        }

    }

    /**
     * 删除
     */
    public void delete(Key key){
        delete(root,key);
    }
    /**
     * 删除
     */
    public Node delete(Node x,Key key){
        //x树为null
        if(x == null){
            return null;
        }

        int cmp = key.compareTo(x.key);
        if(cmp>0){
            //如果key比x节点的键大,则查找x的右子树
            x.right = delete(x.right,key);
        }else if (cmp<0){
            //如果key比x节点的键小,则查找x的左子树
            x.left = delete(x.left,key);
        }else {
            N--;
           //如果key等于x节点的键,完成真正删除节点动作
            if(x.right == null){
                return x.left;
            }
            if(x.left == null){
                return x.right;
            }
            //找到右子树中最小的子节点
            Node minNode = x.right;
            while (minNode.left!=null){
                minNode = minNode.left;
            }
            //删除右子树中最小的节点
            Node node = x.right;
            while (node.left!=null){
                if(node.left.left == null){
                    node.left = null;
                }else {
                    node = node.left;
                }
            }
            //让x节点的左子树成为minNode左子树
            minNode.left = x.left;
            //让x节点的右子树成为minNode右子树
            minNode.right = x.right;
            //让x节点的父节点指向minNode
            x = minNode;


        }
        return x;
    }

    public Key min(){
        return min(root).key;
    }
    public Node min(Node x){
        //判断x有没有子左节点,如果有继续找下去,如果没有,则x就是最小的结点
        if(x.left!=null){
            return min(x.left);
        }else {
            return x;
        }
    }

    public Key max(){
        return max(root).key;
    }
    public Node max(Node x){
        //判断x有没有右子节点,如果有继续找下去,如果没有,则x就是最大的结点
        if(x.right!=null){
            return max(x.right);
        }else {
            return x;
        }
    }

    /**
     * 获取二叉树中所有的键
     * @return
     */
    public Queue<Key> preErgodic(){
        Queue<Key> keys = new Queue<>();
        preErgodic(root, keys);
        return keys;
    }

    /**
     * 使用前序遍历,获取二叉树中所有的键,并放到队列key中
     * @return
     */
    public void preErgodic(Node x,Queue<Key> keys){

        if(x==null){
            return;
        }
        //把x节点的key放入到key中
        keys.enqueue(x.key);

        //递归遍历x节点的左子树
        if(x.left !=null){
            preErgodic(x.left,keys);
        }

        //递归遍历x节点的右子树
        if(x.right !=null){
            preErgodic(x.right,keys);
        }

    }
    /**
     * 获取二叉树中所有的键
     * @return
     */
    public Queue<Key> midErgodic(){
        Queue<Key> keys = new Queue<>();
        midErgodic(root, keys);
        return keys;
    }

    /**
     * 使用中序获取二叉树中所有的键,并放到队列key中
     * @return
     */
    public void midErgodic(Node x,Queue<Key> keys){
        if(x==null){
            return;
        }

        //先递归,把左子树的键放到keys中
        if(x.left!=null){
            midErgodic(x.left,keys);
        }

        //把当前节点x的键放到keys中
        keys.enqueue(x.key);

        //在递归,把右子树的键放到keys中
        if(x.right!=null){
            midErgodic(x.right,keys);
        }

    }

    /**
     * 获取二叉树中所有的键
     * @return
     */
    public Queue<Key> afterErgodic(){
        Queue<Key> keys = new Queue<>();
        afterErgodic(root, keys);
        return keys;
    }

    /**
     * 使用中序获取二叉树中所有的键,并放到队列key中
     * @return
     */
    public void afterErgodic(Node x,Queue<Key> keys){
        if(x==null){
            return;
        }

        //先递归,把左子树的键放到keys中
        if(x.left!=null){
            midErgodic(x.left,keys);
        }
        //在递归,把右子树的键放到keys中
        if(x.right!=null){
            midErgodic(x.right,keys);
        }
        //最后把当前节点x的键放到keys中
        keys.enqueue(x.key);



    }
    /**
     * 层序遍历
     * @return
     */
    public Queue<Key> layerErgodic(){
        //定义两个队列,分别存储树中的键和树中的节点
        Queue<Key> keys = new Queue<>();
        Queue<Node> nodes = new Queue<>();

        //默认,往队列中放入根节点
        nodes.enqueue(root);

        while (!nodes.isEmpty()){
            //从队列中弹出一个节点,把key放入到keys队列中
            Node node = nodes.dequeue();
            keys.enqueue(node.key);
            //判断当前节点有没有左子节点,如果有,则放入到nodes中
            if(node.left!=null){
                nodes.enqueue(node.left);
            }
            //判断当前节点有没有右子节点,如果有,则放入到nodes中
            if(node.right!=null){
                nodes.enqueue(node.left);
            }
        }
        return keys;
    }
    public int maxDepth(){
        return maxDepth(root);
    }
    public int maxDepth(Node x){
        if(x==null){
            return 0;
        }
        //x节点的最大深度
        int max = 0;
        //左子树的最大深度
        int maxL = 0;
        //右子树的最大深度
        int maxR = 0;
        if(x.left!=null){
            maxL = maxDepth(x.left);
        }
        //计算x节点右子树的最大深度
        if(x.right!=null){
            maxR = maxDepth(x.right);
        }
        //比较左右子树的最大深度,取最大值+1即可
        max = maxL>maxR ? maxL+1 : maxR+1;

        return max;

    }
}
