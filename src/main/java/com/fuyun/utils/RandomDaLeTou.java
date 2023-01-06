package com.fuyun.utils;

import java.util.*;

/**
 * @describe: 描述
 * @Author: Cai_YF
 * @Date: 2022/9/9 16:56
 * @Version: v1.0
 */
public class RandomDaLeTou {

    private static Random random = new Random();
    //16 32 02 29 25 10 03
    public static void main(String[] args) {
        System.out.println(getStr());
    }
    public static String getStr(){
        List list = new ArrayList(),lastList = new ArrayList();
        String str = "";
        while (true){
            int s = getNum(34);
            str = s<=9?"0"+s:s+"";
            if(!list.contains(str)){
                list.add(str);
            }
            if(list.size()>=5){
                break;
            }
        }
        while (true){
            int s = getNum(11);
            str = s<=9?"0"+s:s+"";
            if(!lastList.contains(str)){
                lastList.add(str);
            }
            if (lastList.size()>=2){
                break;
            }
        }
        list.addAll(lastList);
        return join(list," ");
        // return getNum(34)+","+getNum(34)+","+getNum(34)+","+getNum(34)+","+getNum(34)+","+getNum(11)+","+getNum(11)+",";
    }

    public static int getNum(int num){
        return random.nextInt(num)+1;
    }

    public static String join(Collection var0, String var1) {
        StringBuffer var2 = new StringBuffer();

        for(Iterator var3 = var0.iterator(); var3.hasNext(); var2.append((String)var3.next())) {
            if (var2.length() != 0) {
                var2.append(var1);
            }
        }

        return var2.toString();
    }
}
