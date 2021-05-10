package com.bzz.cloud.start;

import com.bzz.cloud.entity.Page;
import com.bzz.cloud.service.IDownLoadService;
import com.bzz.cloud.service.IProcessService;
import com.bzz.cloud.service.IStoreService;
import com.bzz.cloud.service.impl.ConsoleStoreServiceImpl;
import com.bzz.cloud.service.impl.HttpClientDownLoadServiceImpl;
import com.bzz.cloud.service.impl.YOUKUProcessServiceImpl;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author ：Iscrdr
 * @description：电视剧爬虫执行入口
 * @email ：624003618@qq.com
 * @date ：2020-11-13 04:34
 * @modified By：
 * @version: 1.0.0
 */
public class StartDSJCount {

    private IDownLoadService iDownLoadService;
    private IProcessService iProcessService;
    private IStoreService iStoreService;

    private Queue<String> urlQueue = new ConcurrentLinkedQueue<>();



    public static void main(String[] args) {
        StartDSJCount dsj = new StartDSJCount();

        dsj.setiDownLoadService(new HttpClientDownLoadServiceImpl());
        dsj.setiProcessService(new YOUKUProcessServiceImpl());
        dsj.setiStoreService(new ConsoleStoreServiceImpl());

        String url = "https://v.youku.com/v_show/id_XMzk4NDE2Njc4OA==.html?spm=a2h0c.8166622.PhoneSokuProgram_1.dselectbutton_1&showid=efbfbd5ac9aaefbfbd5b";
        url = "https://www.youku.com/category/show";
        dsj.urlQueue.add(url);
        dsj.startSpider();

    }

    /**
     * 开启一个爬虫入口
     */
    public void startSpider(){
        while (true){
            //从队列中提取需要解析的url
            String url = urlQueue.poll();
            if(StringUtils.isNotBlank(url)){
                Page page = this.downloadPage(url);
                page.setUrl(url);
                this.processPage(page);
                List<String> urlList = page.getUrlList();
                for(String url1 : urlList){
                    this.urlQueue.add(url1);
                }
                if(page.getUrl().startsWith("https://v.youku.com/v_show")){
                    //详情页
                    page.setUrl(url);
                    this.storePage(page);
                }
            }else {
                System.out.println("队列中的数据为空");
                break;
            }
        }
    }




    public void storePage(Page page){
        this.iStoreService.store(page);
    }

    public void processPage(Page page){
        this.iProcessService.process(page);
    }

    public Page downloadPage(String url){
        return this.iDownLoadService.download(url);
    }

    public IDownLoadService getiDownLoadService() {
        return iDownLoadService;
    }

    public void setiDownLoadService(IDownLoadService iDownLoadService) {
        this.iDownLoadService = iDownLoadService;
    }

    public IProcessService getiProcessService() {
        return iProcessService;
    }

    public void setiProcessService(IProcessService iProcessService) {
        this.iProcessService = iProcessService;
    }

    public IStoreService getiStoreService() {
        return iStoreService;
    }

    public void setiStoreService(IStoreService iStoreService) {
        this.iStoreService = iStoreService;
    }
}
