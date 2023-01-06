package com.fuyun.core;

import com.fuyun.entity.Novel;
import com.fuyun.utils.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @describe: 获取知轩藏书仙草榜
 * @Author: Cai_YF
 * @Date: 2022/5/12 16:31
 * @Version: v1.0
 */
public class NovelList {

    private static Random random = new Random();

    // 获取详情
    private static String detailUrl = "http://www.zxcs.me/post/{id}";
    // 获取仙草毒草
    private static String getUrl = "http://www.zxcs.me/content/plugins/cgz_xinqing/cgz_xinqing_action.php?action=show&id={id}&m="+random.nextDouble();


    // 开一百个线程跑回卡死人家的网站,32个应该差不多了
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(16,500, 60,TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(30000));


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int count = 25535;
        List<Novel> novelList = new ArrayList<>();
        List<Future<Novel>> futureList = new ArrayList<>();
        for (int i = 2000; i < count; i++) {
            // executor
            int finalI = i;
            futureList.add(executor.submit(()->{return getNovel(finalI +"");}));
        }
        for (Future<Novel> novelFuture : futureList) {
            Novel novel = novelFuture.get();
            if(novel!=null){
                novelList.add(novel);
            }
        }
        // 倒序排序
        List<Novel> collect = novelList.stream().sorted(Comparator.comparing(Novel::getXianCao)
                .reversed()).collect(Collectors.toList());
        for (int i = 0; i < 100; i++) {
            System.out.println(collect.get(i));
        }
        System.exit(0);
    }

    public static Novel getNovel(String id){
        String getDetailUrl = detailUrl.replace("{id}",id);
        String result = HttpUtil.sendGet(getDetailUrl);
        // System.out.println(result);
        // String content = result.substring(result.indexOf("【内容简介】："));
        String webTitle = getTitle(result);
        if(webTitle.equals("404") || !webTitle.contains("作者") ){
            return null;
        }
        String title = webTitle.substring(webTitle.indexOf("《")+1,webTitle.indexOf("》"));
        String author = webTitle.substring(webTitle.indexOf("作者：")+3 ,webTitle.indexOf(" -"));

        String result2 = HttpUtil.sendGet(getUrl.replace("{id}",id));
        // 仙草,粮草,干草,枯草,毒草
        String ss [] = result2.split(",");
        Novel novel = new Novel();
        novel.setId(id);
        novel.setTitle(title);
        novel.setAuthor(author);
        novel.setDetailUrl(getDetailUrl);
        novel.setXianCao(Integer.parseInt(ss[0]));
        novel.setLiangCao(Integer.parseInt(ss[1]));
        novel.setGangCao(Integer.parseInt(ss[2]));
        novel.setKuCao(Integer.parseInt(ss[3]));
        novel.setDuCao(Integer.parseInt(ss[4]));
        return novel;
    }

    /**
     * 获取网页的标题
     *
     * @return
     */
    public static String getTitle(String content) {

        String searchTitle = "(<title>|<TITLE>)(.*?)(</title>|</TITLE>)"; // 获取网页的标题的正则表达式
        Pattern pattern = Pattern.compile(searchTitle); // 获得content
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            return matcher.group(2);
        }
        return null;

    }
}
