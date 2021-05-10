package com.bzz.cloud.service.impl;

import com.bzz.cloud.entity.Page;
import com.bzz.cloud.service.IProcessService;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

/**
 * @author ：Iscrdr
 * @description：
 * @email ：624003618@qq.com
 * @date ：2020-11-13 04:44
 * @modified By：
 * @version:
 */
public class YOUKUProcessServiceImpl implements IProcessService {


    @Override
    public void process(Page page) {

        String content = page.getContent();
        HtmlCleaner htmlCleaner = new HtmlCleaner();
        TagNode rootNode = htmlCleaner.clean(content);

        if(page.getUrl().startsWith("https://v.youku.com/v_show")){
            parseDetail(page,rootNode);
        }else {
            try {

                String regMatch ;
                int count = 0;
                Object[] objects ;
                while (true){
                    count ++;
                    regMatch = "//*[@id=\"app\"]/div/div[2]/div[3]/div/div/div[" + count + "]/div/div/div[1]/a";
                    objects = rootNode.evaluateXPath(regMatch);
                    if(null != objects && objects.length>0){
                        TagNode tagNode = (TagNode)objects[0];
                        String href = tagNode.getAttributeByName("href");

                        if( !href.startsWith("http") || !href.startsWith("https")){
                            href = "https:"+href;
                        }
                        System.out.println(href);
                        page.addUrl(href);
                        System.out.println("第一页第"+count+"个电视剧解析完成");
                    }else {
                        break;
                    }
                }

            } catch (XPatherException e) {
                e.printStackTrace();
            }

        }





    }

    /**
     * 解析电视剧详情页
     * @param page
     * @param rootNode
     */
    public void parseDetail(Page page,TagNode rootNode){

        try {
            //解析详情页
            Object[] objects = rootNode.evaluateXPath("//*[@id=\"app\"]/div/div[2]/div[2]/div[2]/div/div/div/div/span/span[2]");
            if(null != objects && objects.length>0){
                TagNode node = (TagNode) objects[0];
                CharSequence text = node.getText();
                //总播放量
                page.setAllnumber(text.toString());
                System.out.println("总播放量: "+text.toString());

            }else {
                System.out.println("========== 没有解析到播放数据 ============");
            }
            Object[] comment = rootNode.evaluateXPath("//*[@id=\"app\"]/div/div[2]/div[2]/div[2]/div/div/div/div/div[1]");
            if(null != comment && comment.length>0){
                TagNode node = (TagNode) comment[0];
                CharSequence text = node.getText();
                //评分
                page.setCommentnumber(text.toString());
                System.out.println("评分:"+text.toString());

            }else {
                System.out.println("========== 没有解析到评分数据 ============");
            }

            page.setSupportnumber(page.getAllnumber());
            page.setDaynumber("0");
            page.setCollectnumber("0");
            page.setAgainstnumber("0");

            Object[] tvName = rootNode.evaluateXPath("//*[@id=\"left-title-content-wrap\"]/div/a");
            if(null != tvName && tvName.length>0){
                TagNode node = (TagNode) tvName[0];
                CharSequence text = node.getText();
                //电视剧名称
                page.setTvname(text.toString());
                System.out.println("电视剧名称:"+text.toString());

            }else {
                System.out.println("========== 没有解析到电视剧名称 ============");
            }

        } catch (XPatherException e) {
            e.printStackTrace();
        }
    }

}
