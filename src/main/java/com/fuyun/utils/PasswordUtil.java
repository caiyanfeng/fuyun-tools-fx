package com.fuyun.utils;

import java.util.*;

/**
 * @describe: 随机密码生成工具类
 * @Author: Cai_YF
 * @Date: 2022/1/27 12:17
 * @Version: v1.0
 */
public class PasswordUtil {

    // 33 64 95 35 36
    private final static String [] specStr = {"!","@","_","#","$"};
    // 特殊字符最大出现次数
    private final static int maxSpecNum = 4;
    public static List<String> listStr = new ArrayList<>();
    private static final Random random = new Random();



    public static void main(String[] args) {
        listStr = Arrays.asList("num","char","spec");
        System.out.println(getPassword(6));
    }

    public static String getPassword(int length){
        StringBuffer stringBuffer = new StringBuffer();
        int specNum = 0,listSize = listStr.size();
        for(int i = 0; i < length; i++) {
            String str =listStr.get(random.nextInt(listSize)) ;
            if( "char".equals(str) ) {
                stringBuffer.append((char)(random.nextInt(26) + 97));
            } else if( "CHAR".equals(str) ) {
                stringBuffer.append( (char)(random.nextInt(26) + 65));
            } else if( "num".equals(str) ) {
                stringBuffer.append( random.nextInt(10));
            } else if( "spec".equals(str) ) {
                stringBuffer.append( specStr[random.nextInt(5)] );
                specNum ++;
                if(maxSpecNum>0&&specNum>=maxSpecNum&&listSize!=1){
                    listSize = listSize-1;
                }
            }
        }
        return stringBuffer.toString();
    }

    public static void checked(boolean isCheck,int num,String type){
        if (isCheck){
            listStr.add(num,type);
        }else {
            // 如果list 长度少于等于1就不能删除
            if(listStr!=null&&listStr.size()>1){
                listStr.remove(type);
            }
        }
    }

    public String initRandomStr(String type,int num){
        StringBuffer stringBuffer = new StringBuffer();
        
        return stringBuffer.toString();
    }
}
