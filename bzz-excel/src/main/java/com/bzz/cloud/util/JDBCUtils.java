package com.bzz.cloud.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @Author:624003618@qq.com
 */
public class JDBCUtils {
    //private static String url = "jdbc:mysql://192.168.132.150:3306/rcsjfx?useUnicode=true?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true&autoReconnect=true&failOverReadOnly=false&maxReconnects=10";

    private static String url = "jdbc:mysql://118.89.237.130:3306/rcsjfx?useUnicode=true&characterEncoding=utf-8&rewriteBatchedStatements=true&useSSL=false";
    //private static String password = "root";

    private static String user = "root";
    private static String password = "kaqkwgisshwqhs9wh";

    private static SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);

    public static String getSql(List<Object> list){
        if(list == null || list.size() <= 0){
            return null;
        }

        StringBuilder sql = new StringBuilder("INSERT INTO `rcsjfx`.`c_cust_sale_2010`(`ID`, `gongchang`, `daqu`, `chengshi`, `yewuyuan`, `cust_no`, `cust_name`, `dapinleimiaoshu`, `yijipinleimiaoshu`, `erjipinleimiaoshu`, `sanjipinleimiaoshu`, `chanpinxianmiaoshu`, `wuliaobianma`, `wuliaomiaoshu`, `xiang`, `dun`, `xiaoshoushouru`, `jingzhi`, `shuie`, `zhanlvjine`, `zhekoujine`, `zhekoubili`, `shoudafangjiancheng`, `fapiaoshiqi`, `dingdanbianhao`, `danjuriqi`, `kucundidian`, `caigoubianhao`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES ") ;

        if(list != null && list.size()>0){
            for (int i=0;i<list.size();i++){
                List row = (List) list.get(i);
                if(row.get(0)==null || row.get(0).toString().equals("工厂描述")){
                    continue;
                }

                sql.append("(");
                //生成id
                sql.append(idWorker.nextId()+",");
                for(int j=0;j<row.size();j++){
                    Object value = row.get(j);

                    if(value == null ){
                        value = "";
                    }

                    //第1~13,21,23,25,26列:数据类型为字符串
                   if(j==0  || j==1  || j==2  || j==3  || j==4 || j==5 ||
                      j==6  || j==7  || j==8  || j==9  || j==10 || j== 11 ||
                      j==12 || j==21 || j==23 || j==25 || j==26){
                       value = "\""+value.toString()+"\"";
                   }
                    Double strDouble = 0.00;
                   //第14~20列：数据类型为double
                    if(j==13 || j==14 || j==15 || j==16 || j==17 || j==18 || j==19 || j== 20){
                        String valueStr = value.toString();
                        value =  getDouble(value);

                    }
                    //第22，24列数据类型为日期
                    if(j==22 || j==24){
                        Calendar calendar = new GregorianCalendar(1900,0,-1);
                        Date d = calendar.getTime();
                        String valueDate = value.toString();
                        if(StringUtils.isBlank(valueDate)){
                            value = "";
                        }else {
                            Date date = DateUtils.addDays(d,Integer.valueOf(valueDate));
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String format = sdf.format(date);
                            value = "\""+format+"\"";
                        }
                        //value = new java.util.Date() ;

                    }
                    sql.append("str_to_date('"+value+"','%m.%d.%Y %h:%i:%s')");
                    sql.append(",");


                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String nowDate = sdf.format(new java.util.Date());
                //公共数据
                sql.append("\""  + "admin" + "\""  );
                sql.append(",");
                //sql.append("\"" + nowDate + "\"" );

                sql.append("str_to_date('"+nowDate+"','%m.%d.%Y %h:%i:%s')");
                sql.append(",");
                sql.append("\""  + "admin" + "\""  );
                sql.append(",");

                sql.append("str_to_date('"+nowDate+"','%m.%d.%Y %h:%i:%s')");
                sql.append(",");
                sql.append("\"" +"企业销售数据：手工使用jdbc导入" + "\"");
                sql.append(",");
                sql.append("0");
                sql.append(")");
                sql.append(",");

                if(i==5){
                    System.out.println(sql.toString());
                }
            }
        }
        //去掉最后一个逗号
        if(sql.toString().endsWith(",")){
            sql.deleteCharAt(sql.length()-1);
        }
        sql.append(";");
        return sql.toString();

    }

    public static String getSqlStr(int size){
        StringBuilder builder = new StringBuilder("INSERT INTO `rcsjfx`.`c_cust_sale_2010`(`ID`, `gongchang`, `daqu`, `chengshi`, `yewuyuan`, `cust_no`, `cust_name`, `dapinleimiaoshu`, `yijipinleimiaoshu`, `erjipinleimiaoshu`, `sanjipinleimiaoshu`, `chanpinxianmiaoshu`, `wuliaobianma`, `wuliaomiaoshu`, `xiang`, `dun`, `xiaoshoushouru`, `jingzhi`, `shuie`, `zhanlvjine`, `zhekoujine`, `zhekoubili`, `shoudafangjiancheng`, `fapiaoshiqi`, `dingdanbianhao`, `danjuriqi`, `kucundidian`, `caigoubianhao`, `create_by`, `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`) VALUES");
        for(int i=0;i<size;i++){
            builder.append("(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            if(i<size-1){
                builder.append(", ");
            }else {
                builder.append(";");
            }
        }
        return builder.toString();
    }



    public static void executeSql(List<Object> list){
        Connection conn = null;
        PreparedStatement pstm = null; ;
        conn = DBUtil.getConnection();
        String sql = "INSERT  INTO c_cust_sale_2019 (ID, gongchang, daqu, chengshi, yewuyuan\n" +
                "\t, cust_no, cust_name, dapinleimiaoshu, yijipinleimiaoshu, erjipinleimiaoshu\n" +
                "\t, sanjipinleimiaoshu, chanpinxianmiaoshu, wuliaobianma, wuliaomiaoshu, xiang\n" +
                "\t, dun, xiaoshoushouru, jingzhi, shuie, zhanlvjine\n" +
                "\t, zhekoujine, zhekoubili, shoudafangjiancheng, fapiaoshiqi, dingdanbianhao\n" +
                "\t, danjuriqi, kucundidian, caigoubianhao, create_by, create_date\n" +
                "\t, update_by, update_date, remarks, del_flag)\n" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


        long startTime1 = System.currentTimeMillis();
            try {
                pstm = conn.prepareStatement(sql);
                conn.setAutoCommit(false);
                for(int i=0;i<list.size();i++) {
                    List rowList = (List) list.get(i);
                    if(rowList.get(0)==null || rowList.get(0).toString().equals("工厂描述") || rowList.get(0).toString().equals("工厂")){
                        continue;
                    }
                    if(list.size()<25){
                        System.out.println("第"+i+"行数据有误："+list.toString());
                        continue;
                    }
                        pstm.setLong(1, idWorker.nextId());
                        pstm.setString(2, rowList.get(0)==null?"":rowList.get(0).toString());
                        pstm.setString(3, rowList.get(1)==null?"":rowList.get(1).toString());
                        pstm.setString(4, rowList.get(2)==null?"":rowList.get(2).toString());
                        pstm.setString(5, rowList.get(3)==null?"":rowList.get(3).toString());
                        pstm.setString(6, rowList.get(4)==null?"":rowList.get(4).toString());
                        pstm.setString(7, rowList.get(5)==null?"":rowList.get(5).toString());
                        pstm.setString(8, rowList.get(6)==null?"":rowList.get(6).toString());
                        pstm.setString(9, rowList.get(7)==null?"":rowList.get(7).toString());
                        pstm.setString(10, rowList.get(8)==null?"":rowList.get(8).toString());
                        pstm.setString(11, rowList.get(9)==null?"":rowList.get(9).toString());
                        pstm.setString(12, rowList.get(10)==null?"":rowList.get(10).toString());
                        pstm.setString(13, rowList.get(11)==null?"":rowList.get(11).toString());
                        pstm.setString(14, rowList.get(12)==null?"":rowList.get(12).toString());


                        Object xiang = null;
                        try{
                            xiang = rowList.get(13);

                        }catch (IndexOutOfBoundsException e){
                            System.out.println("第"+i+"行数据有错误，数据列数："+rowList.size());
                            System.out.println("详细数据："+rowList.toString());
                            e.printStackTrace();
                        }

                        Double xiangDouble = getDouble(xiang);
                        pstm.setDouble(15, xiangDouble);

                        Object dun = rowList.get(14);
                        Double dunDouble = getDouble(dun);
                        pstm.setDouble(16, dunDouble);

                        Object  xiaoshoushouru = rowList.get(15);
                        Double xiaoshoushouruDouble = getDouble(xiaoshoushouru);
                        pstm.setDouble(17, xiaoshoushouruDouble);

                    Object jingzhi = rowList.get(16);
                        Double jingzhiDouble = getDouble(jingzhi);
                        pstm.setDouble(18, jingzhiDouble);

                    Object shuie = rowList.get(17);
                        Double shuieDouble = getDouble(shuie);
                        pstm.setDouble(19, shuieDouble);

                    Object zhanlvjiajine = rowList.get(18);
                        Double zhanlvjiajineDouble = getDouble(zhanlvjiajine);
                        pstm.setDouble(20, zhanlvjiajineDouble);

                    Object zhekoujine = rowList.get(19);
                        Double zhekoujineDouble = getDouble(zhekoujine);
                        pstm.setDouble(21, zhekoujineDouble);

                    Object zhekoubaifenbi = rowList.get(20);

                        Double zhekoubaifenbiDouble = getDouble(zhekoubaifenbi);
                        pstm.setDouble(22, zhekoubaifenbiDouble);

                        pstm.setString(23, rowList.get(21)==null?"":rowList.get(21).toString());

                        Object fapiaoriqi = rowList.get(22);
                        java.sql.Date fapiaoriqiDate = getDate(fapiaoriqi);
                        pstm.setDate(24, fapiaoriqiDate);

                        pstm.setString(25, rowList.get(23)==null?"":rowList.get(23).toString());

                    Object danjuriqi = rowList.get(24);
                        java.sql.Date danjuriqiDate = getDate(danjuriqi);
                        pstm.setDate(26, danjuriqiDate);

                        pstm.setString(27, rowList.get(25)==null?"":rowList.get(25).toString());
                        pstm.setString(28, rowList.get(26)==null?"":rowList.get(26).toString());
                        pstm.setString(29, "admin");

                        pstm.setDate(30, new java.sql.Date(new java.util.Date().getTime()));
                        pstm.setString(31, "admin");
                        pstm.setDate(32, new java.sql.Date(new java.util.Date().getTime()));
                        pstm.setString(33, "企业销售数据：手工使用jdbc导入");
                        pstm.setString(34, "0");

                    pstm.addBatch();
                    if ((i % 10000) == 0){
                        pstm.executeBatch();
                        conn.commit();
                        pstm.clearBatch();
                        long endTime = System.currentTimeMillis();
                        System.out.println("第" + i + "条导入到数据库,耗时："+(endTime-startTime1) +"ms");
                        startTime1 = System.currentTimeMillis();
                    }
                }


                pstm.executeBatch();
                conn.commit();
                pstm.clearBatch();
                long endTime2 = System.currentTimeMillis();
                System.out.println("共有" + list.size() + "条导入到了数据库,共耗时："+(endTime2-startTime1)+"ms");

            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                DBUtil.closeConnection(conn);

            }


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
