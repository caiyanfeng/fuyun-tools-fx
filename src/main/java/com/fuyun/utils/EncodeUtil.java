package com.fuyun.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @describe: 描述
 * @Author: Cai_YF
 * @Date: 2022/9/23 10:39
 * @Version: v1.0
 */
public class EncodeUtil {

    public static void main(String[] args) {
        System.out.println(unicodeEncode("测试范德萨范德萨额"));
        System.out.println(unicodeDecode("\\u975e\\u6cd5\\u8bf7\\u6c42\\uff1a\\u7b7e\\u540d\\u9519\\u8bef"));
    }

    /**
     * @param string
     * @return 转换之后的内容
     * @Title: unicodeDecode
     * @Description: unicode解码 将Unicode的编码转换为中文
     */
    public static String unicodeDecode(String string) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(string);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            string = string.replace(matcher.group(1), ch + "");
        }
        return string;
    }

    /**
     * @Title: unicodeEncode
     * @Description: unicode编码 将中文字符转换成Unicode字符
     * @param string
     * @return
     */
    public static String unicodeEncode(String string) {
        char[] utfBytes = string.toCharArray();
        String unicodeBytes = "";
        for (int i = 0; i < utfBytes.length; i++) {
            String hexB = Integer.toHexString(utfBytes[i]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        return unicodeBytes;
    }

}
