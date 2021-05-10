package com.bzz.cloud;

import com.bzz.cloud.utils.JDBCUtils;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App2 {
    public static void main( String[] args ){
        ITesseract instance = new Tesseract();
        // 语言库位置（修改为跟自己语言库文件夹的路径）
        String lagnguagePath = "D:\\testdata\\language";
        //设置训练库的位置
        instance.setDatapath(lagnguagePath);
        //设置中文字库
        instance.setLanguage("chi_sim");
        List<List<Object>> list = new ArrayList();
        String result ;
        try {
            long startTime = System.currentTimeMillis();

            File imageFile = new File("D:\\testdata\\tree");
            if(imageFile.isDirectory()){
                File[] files = imageFile.listFiles();

                for(File file :files){
                    result =  instance.doOCR(file);
                    System.out.println(result);
                    long endTime = System.currentTimeMillis();
                    System.out.println("Time is：" + (endTime - startTime) + " 毫秒");

                    //开始解析
                    //1.去除空格
                    String result2 = result.replaceAll("\\s", "")
                            .replaceAll("几凤", "凤凰")
                            .replaceAll("几凰", "凤凰")
                            .replaceAll("凤凤", "凤凰")
                            .replaceAll("“", "")
                            .replaceAll("”", "")
                            .replaceAll("\t","")
                            .replaceAll("2019年","\r\n2019年");
                    System.out.println(result2);
                    String[] lines = result2.split("\r\n");
                    for(int i=0;i<lines.length;i++){
                        List<Object> list1 = new ArrayList<>();
                        String line = lines[i];
                        System.out.println(line);
                        if(line == "" || line == null || line.length()<10 || line.startsWith("门一一")){
                            continue;
                        }
                        //1.提取时间
                        String[] lineStr1 = line.split("分");
                        String timeStr = lineStr1[0]+"分";
                        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        Date date = sdf1.parse(timeStr);
                        String format = sdf2.format(date);

                        //2.提取区服
                        String qufu = lineStr1[1];
                        System.out.println(qufu);
                        String qufuname = qufu.substring(qufu.indexOf("凤"), qufu.indexOf("服]")+1);

                        //3.提取名称
                        String nickname = qufu.substring(qufu.indexOf("服]")+2, qufu.indexOf("击"));

                        //4.提取boss
                        String bossname = qufu.substring(qufu.indexOf("击杀了")+3, qufu.indexOf(",获"));

                        //5.提取星级
                        String level = qufu.substring(qufu.indexOf("获得了")+3, qufu.indexOf("星["));

                        //6.提取魂核名称
                        String hunhename = qufu.substring(qufu.indexOf("魂"), qufu.indexOf("核]")+1);

                        System.out.println(format+","+qufuname+","+nickname+","+bossname+","+level+","+hunhename);

                        if(!list1.contains(format) && !list1.contains(qufuname) && !list1.contains(nickname) && !list1.contains(bossname)&& !list1.contains(level) && !list1.contains(hunhename)
                        ){
                            list1.add(format);
                            list1.add(qufuname);
                            list1.add(nickname);
                            list1.add(bossname);
                            list1.add(level);
                            list1.add(hunhename);
                        }
                        list.add(list1);
                    }

                }
            }
            System.out.println(list);
           JDBCUtils.executeSql(list);
        } catch (TesseractException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
