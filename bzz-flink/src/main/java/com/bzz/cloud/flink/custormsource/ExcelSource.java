package com.bzz.cloud.flink.custormsource;

import com.bzz.cloud.utils.ExcelUtils;
import org.apache.flink.streaming.api.functions.source.RichSourceFunction;

import java.util.List;

/**
 * @description：从Excel中加载数据
 * @author     ：Iscrdr
 * @email      ：624003618@qq.com
 * @date       ：2020/01/16 3:34
 * @modified By：
 * @version: 1.0.0$
 */
public class ExcelSource extends RichSourceFunction<List> {

    private String path;


    public ExcelSource(String path) {
        this.path = path;
    }

    @Override
    public void run(SourceContext<List> ctx) throws Exception {
        List<List<Object>> excelData = ExcelUtils.getExcelData(path);
        ctx.collect(excelData);
    }

    @Override
    public void cancel() {

    }
}
