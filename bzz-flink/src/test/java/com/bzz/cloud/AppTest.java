package com.bzz.cloud;

import com.bzz.cloud.utils.ExcelUtils;
import org.junit.Test;
import java.io.IOException;
import java.util.List;


public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void getExcelData() throws IOException {
        List<List<Object>> excelData = ExcelUtils.getExcelData("E:\\excetodb\\福临门2018-06月销售.xlsx");
        for(int i=0;i<excelData.size();i++){
            List<Object> rowList = excelData.get(i);
            System.out.println(rowList.toString());
        }

    }
}
