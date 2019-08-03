package com.bzz.cloud;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.bzz.cloud.listener.CCustSaleListener;
import com.bzz.cloud.model.CCustSale;
import com.bzz.cloud.util.FileUtil;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class CustModelApp {
    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        InputStream inputStream = FileUtil.getFileInputStream("D:\\excetodb\\福临门2018-07月销售.xlsx");
        CCustSaleListener excelListener = new CCustSaleListener();
        EasyExcelFactory.readBySax(inputStream,new Sheet(1, 1), excelListener);
        List<CCustSale> data = excelListener.getData();
        System.out.println(data.size());
        long endTime = System.currentTimeMillis();
        System.out.println("耗时："+(endTime-startTime)+"ms");


    }
}
