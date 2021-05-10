package com.bzz.cloud.utils;

import com.bzz.cloud.entity.Page;
import com.bzz.cloud.service.impl.HttpClientDownLoadServiceImpl;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author ：Iscrdr
 * @description：页面下载工具类
 * @email ：624003618@qq.com
 * @date ：2020-11-13 03:48
 * @modified By：
 * @version: 1.0.0
 */
public class PageDownloadUtil {

    private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.193 Safari/537.36 Edg/86.0.622.68";

    public static  String getPageContent(String url){
        HttpClientBuilder builder = HttpClients.custom();
        CloseableHttpClient client = builder.build();


        HttpGet request = new HttpGet(url);
        request.setHeader("User-Agent",USER_AGENT);
        String countent = "";
        try {
            CloseableHttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            countent = EntityUtils.toString(entity);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return countent;

    }



    public static void main(String[] args) {
        String url = "https://v.qq.com/x/cover/m441e3rjq9kwpsc.html";


        HttpClientDownLoadServiceImpl hp = new HttpClientDownLoadServiceImpl();
        Page page = hp.download(url);

        System.out.println(page.getContent());


    }


}
