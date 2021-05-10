package com.bzz.cloud.utils;/**
 * @description：Excel监听器：读取Excel中的数据
 * @author ：Iscrdr
 * @email ：624003618@qq.com
 * @date ：2020/01/16 3:05
 * @modified By：
 * @version: 1.0.0$
 */

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description：Excel监听器：读取Excel中的数据
 * @author     ：Iscrdr
 * @email      ：624003618@qq.com
 * @date       ：2020/01/16 3:05
 * @modified By：
 * @version: 1.0.0$
 */
public class ExcelListener extends AnalysisEventListener {

    public final static List<List<Object>> data = new ArrayList<>();

    public List<List<Object>> getData() {
        return data;
    }

    @Override
    public void invoke(Object object, AnalysisContext context) {
        if(object != null){
            List<Object> rowList = (List<Object>)object;
            data.add(rowList);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

}
