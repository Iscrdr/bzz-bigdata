package com.bzz.cloud.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : yang qianli
 * @email: 624003618@qq.com
 * @Date: 2019-05-30 13-15
 * @Modified by:
 * @Description:
 */
public class ExcelListener extends AnalysisEventListener {


    public static List<Object> data = new ArrayList<Object>();

    @Override
    public void invoke(Object object, AnalysisContext context) {
       // System.out.println(context.getCurrentSheet());
        data.add(object);
        /*if(data.size()>=100){
            doSomething();
            data = new ArrayList<Object>();
        }*/
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
       // doSomething();
    }
    public void doSomething(){
        /*for (Object o:data) {
            System.out.println(o);
        }*/
    }

}