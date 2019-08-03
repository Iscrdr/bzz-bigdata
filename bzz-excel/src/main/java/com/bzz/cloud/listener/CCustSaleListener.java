package com.bzz.cloud.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.bzz.cloud.model.CCustSale;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Author : yang qianli
 * @email: 624003618@qq.com
 * @Date: 2019-05-30 13-15
 * @Modified by:
 * @Description:
 */
public class CCustSaleListener extends AnalysisEventListener{


    public final static List<CCustSale> data = new ArrayList<>();

    @Override
    public void invoke(Object object, AnalysisContext context) {
        if(object != null){
            List rowList = (List)object;
            CCustSale cs = new CCustSale();
            for(int i=0;i<rowList.size();i++){
                String gongchang =  rowList.get(0)==null?"":rowList.get(0).toString();
                cs.setGongchang(gongchang);
                String daqu = rowList.get(1)==null?"":rowList.get(1).toString();
                cs.setDaqu(daqu);
                String chengshiqun = rowList.get(2)==null?"":rowList.get(2).toString();
                cs.setChengshi(chengshiqun);
                String yewuyuan = rowList.get(3)==null?"":rowList.get(3).toString();
                cs.setYewuyuan(yewuyuan);
                String kehudaima = rowList.get(4)==null?"":rowList.get(4).toString();
                cs.setKucundidian(kehudaima);
                String custName = rowList.get(5)==null?"":rowList.get(5).toString();
                cs.setCustName(custName);
                String dapinleimiaoshu = rowList.get(6)==null?"":rowList.get(6).toString();
                cs.setDapinleimiaoshu(dapinleimiaoshu);
                String yijipinleimiaoshu = rowList.get(7)==null?"":rowList.get(7).toString();
                cs.setYijipinleimiaoshu(yijipinleimiaoshu);
                String erjipinleimiaoshu = rowList.get(8)==null?"":rowList.get(8).toString();
                cs.setErjipinleimiaoshu(erjipinleimiaoshu);
                String sanjipinleimiaoshu = rowList.get(9)==null?"":rowList.get(9).toString();
                cs.setSanjipinleimiaoshu(sanjipinleimiaoshu);
                String chanpinxianmiaoshu = rowList.get(10)==null?"":rowList.get(10).toString();
                cs.setChanpinxianmiaoshu(chanpinxianmiaoshu);
                String wuliaobianma = rowList.get(11)==null?"":rowList.get(11).toString();
                cs.setWuliaobianma(wuliaobianma);
                String wuliaomiaoshu = rowList.get(12)==null?"":rowList.get(12).toString();
                cs.setWuliaomiaoshu(wuliaomiaoshu);

                Double xiang = getDouble(rowList.get(13));
                cs.setXiang(xiang);
                Double  dun = getDouble(rowList.get(14));
                cs.setDun(dun);
                Double  xiaoshoushouru = getDouble(rowList.get(15));
                cs.setXiaoshoushouru(xiaoshoushouru);
                Double jingzhi = getDouble(rowList.get(16));
                cs.setJingzhi(jingzhi);
                Double shuie = getDouble(rowList.get(17));
                cs.setShuie(shuie);
                Double zhanlvjiajine = getDouble(rowList.get(18));
                cs.setZhanlvjine(zhanlvjiajine);
                Double zhekoujine = getDouble(rowList.get(19));
                cs.setZhekoujine(zhekoujine);
                Double zhekoubaifenbi = getDouble(rowList.get(20));
                cs.setZhekoubili(zhekoubaifenbi);

                String shoudafangjincheng = rowList.get(21)==null?"":rowList.get(21).toString();
                cs.setShoudafangjiancheng(shoudafangjincheng);

                Date fapiaoriqi =  getDate(rowList.get(22));
                cs.setFapiaoshiqi(fapiaoriqi);
                String dingdanhaoma = rowList.get(23)==null?"":rowList.get(23).toString();
                cs.setDingdanbianhao(dingdanhaoma);
                Date danjuriqi = getDate(rowList.get(24));
                cs.setDanjuriqi(danjuriqi);
                String kuchengdidian = rowList.get(25)==null?"":rowList.get(25).toString();
                cs.setKucundidian(kuchengdidian);
                String caigoudingdanhao = rowList.get(26)==null?"":rowList.get(26).toString();
                cs.setCaigoubianhao(caigoudingdanhao);
                String createUser = "admin";
                cs.setCreateBy(createUser);
                Date createDate = new java.sql.Date(new java.util.Date().getTime());
                cs.setCreateDate(createDate);
                String updateUser = "admin";
                cs.setUpdateBy(updateUser);
                Date updateDate =  new java.sql.Date(new java.util.Date().getTime());
                cs.setUpdateDate(updateDate);
                String remark = "企业销售数据：手工使用jdbc导入";
                cs.setRemarks(remark);
                String deleteFlag =  "0";
                cs.setDelFlag(deleteFlag);
            }
            data.add(cs);
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
       // doSomething();
    }
    public List<CCustSale> getData(){
       return data;
    }

    public static Double getDouble(Object value){
        if(value == null){
            return 0.00;
        }
        String valueStr = value.toString();
        Double strDouble = 0.00;
        if(valueStr.contains(",")){
            valueStr = valueStr.replaceAll(",","");
        }
        if(valueStr.contains("*")){
            valueStr = valueStr.replaceAll("\\*","");
        }
        if(valueStr.endsWith("-")){
            valueStr = valueStr.substring(0,valueStr.length()-1);
        }
        if(valueStr.contains("%")){
            valueStr = valueStr.replaceAll("%","");
        }
        if(StringUtils.isNotBlank(valueStr) ){
            try {
                BigDecimal db = new BigDecimal(valueStr);
                strDouble = db.setScale(8, BigDecimal.ROUND_HALF_UP).doubleValue();
            }catch (Exception e){
                System.out.println(valueStr);
                e.printStackTrace();
            }

        }else{
            strDouble = 0.00;
        }
        return strDouble;
    }



    public static java.sql.Date getDate(Object value){
        if(value == null){
            return null;
        }

        Calendar calendar = new GregorianCalendar(1900,0,-1);
        Date d = calendar.getTime();
        String valueDate = value.toString();
        Date date = DateUtils.addDays(d,Integer.valueOf(valueDate));

        return new java.sql.Date(date.getTime());
    }
}