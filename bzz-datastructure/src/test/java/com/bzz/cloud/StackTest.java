package com.bzz.cloud;

import com.bzz.cloud.datastructure.stack.Stack;
import org.junit.Test;

/**
 * @author ：Iscrdr
 * @description：栈测试
 * @email ：624003618@qq.com
 * @date ：2021-04-19 02:07
 * @modified By：
 * @version: 1.0.0
 */
public class StackTest {
    @Test
    public void pushStack(){
        Stack<String> stack = new Stack();

        stack.push("aa");
        stack.push("bb");
        stack.push("cc");
        stack.push("dd");

        for(Object item : stack){
            System.out.println(item);
        }

        String pop = stack.pop();
        System.out.println("弹出的元素："+pop);
        System.out.println("剩余元素个数："+stack.size());
    }

    @Test
    public void isMatchTest(){
        String str1 = "北京(上海)(谷)歌广东";
        String str2 = "北京((上海)(谷)歌广东";
        String str3 = "北京(上海)(谷))歌广东";

        System.out.println("str1:"+isMatch(str1));
        System.out.println("str2:"+isMatch(str2));
        System.out.println("str3:"+isMatch(str3));

    }

    /**
     * 逆波兰表达式测试
     */
    @Test
    public void getResultTest(){
        // 3*(17-15) + 18/6
        String [] strs = {"3","17","15","-","*","18","6","/","+"};
        int result = getResult(strs);
        System.out.println("result = " +result);

    }
    /**
     * 判断str中的括号是否匹配
     * @param str
     * @return
     */
    public boolean isMatch(String str) {

        //1,创建栈对象,用来存储左括号
        Stack<Character> stack = new Stack<>();

        //2,从左往右遍历字符串
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (c == '(') {
                //3,判断当前字符串是否死左括号,如果是存入栈中
                stack.push(c);
            } else if (c == ')') {
                //4,判断当前字符是否是右括号,如果是,从栈中弹出左括号
                Character pop = stack.pop();
                if (pop == null) {
                    return false;
                }
            }

        }
        //5,判断栈中还有没有剩余的左括号,如果有则证明不匹配
        if (stack.size() <= 0) {
            return true;
        }
        return false;

    }



    /**
     * 计算逆波兰表达式的结果
     * 3*(17-15)+18/6
     * @param strs : "3,17,15,-,*,18,6,/,+"
     */
    public int getResult(String[] strs) {

        //1,创建栈对象,用来存储左括号
        Stack<String> stack = new Stack<>();

        //2,遍历字符数组
        for (String str : strs) {
            //1,如果是数字,存入到栈中
            //2,如果是运算符,从栈中取出两个数字进行计算,并把结果存入栈中
            String pop1 = "";
            String pop2 = "";
            int result = 0;
            switch (str) {
                case "+":
                    pop1 = stack.pop();
                    pop2 = stack.pop();
                    result = Integer.parseInt(pop1) + Integer.parseInt(pop2);
                    stack.push(result + "");
                    break;
                case "-":
                    pop1 = stack.pop();
                    pop2 = stack.pop();
                    result = Integer.parseInt(pop2) - Integer.parseInt(pop1);
                    stack.push(result + "");
                    break;
                case "*":
                    pop1 = stack.pop();
                    pop2 = stack.pop();
                    result = Integer.parseInt(pop2) * Integer.parseInt(pop1);
                    stack.push(result + "");
                    break;
                case "/":
                    pop1 = stack.pop();
                    pop2 = stack.pop();
                    result = Integer.parseInt(pop2) / Integer.parseInt(pop1);
                    stack.push(result + "");
                    break;
                default:
                    stack.push(str);
                    break;

            }
        }
        return Integer.parseInt(stack.pop());
    }
}
