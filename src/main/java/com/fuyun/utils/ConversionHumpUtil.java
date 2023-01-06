package com.fuyun.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @describe: 描述
 * @Author: Cai_YF
 * @Date: 2022/10/26 15:09
 * @Version: v1.0
 */
public class ConversionHumpUtil {

    private static Pattern UNDERLINE_PATTERN = Pattern.compile("_([a-z])");

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    public static void main(String[] args) {
        System.out.println(lowerLineToHump("game_code\n" +
                "date_str\n" +
                "info_id\n" +
                "expend\n" +
                "unit_price\n" +
                "remark\n" +
                "pay_usd\n" +
                "reg_num\n" +
                "login_dau\n" +
                "pay_dau\n" +
                "rate\n" +
                "ltv1\n" +
                "ltv_cur\n" +
                "roi1\n" +
                "roi_cur\n" +
                "ltv_pro\n"));
        System.out.println(capitalizeTheFirstLetter("nAMe"));
        System.out.println(humpToLowerLine("nameAndAddress"));

    }

    /**
     * @Description: 下划线格式 -> 驼峰  大小写均可
     * <p>
     * NAME_AND_ADDRESS -> nameAndAddress
     * @Author: Yiang37
     * @Date: 2020/11/03 15:26:02
     * @Version: 1.0
     * @method: lowerLineAndUppercaseToHump()
     * @param: [Big_]
     * @return: java.lang.String
     */
    public static String lowerLineToHump(String lowerLineAndUppercaseStr) {
        //拆分成数组
        String[] eachStr = lowerLineAndUppercaseStr.split("_");
        StringBuilder resStr = new StringBuilder();
        String firstStr = "";
        String tempStr = "";
        for (int i = 0; i < eachStr.length; i++) {
            //第一个数组全部小写
            if (i == 0) {
                firstStr = eachStr[0].toLowerCase();
                resStr.append(firstStr);
            } else {
                //以后的数组首字母大写
                tempStr = capitalizeTheFirstLetter(eachStr[i]);
                resStr.append(tempStr);
            }
        }

        return resStr.toString();
    }

    /**
     * @Description: 任意字符串 -> 首字母大写
     * NAME -> Name
     * name -> Name
     * NaMe -> Name
     * @Author: Yiang37
     * @Date: 2020/11/03 16:50:16
     * @Version: 1.0
     * @method: capitalizeTheFirstLetter()
     * @param: [str]
     * @return: java.lang.String
     */
    public static String capitalizeTheFirstLetter(String str) {
        char firstChar = str.toUpperCase().charAt(0);
        String nextStr = str.toLowerCase().substring(1);
        return firstChar + nextStr;
    }

    /**
     * @Description: 驼峰 -> 下划线格式 默认小写,存在第二个形参且为true时大写.
     * @Author: Yiang37
     * @Date: 2020/11/03 17:10:25
     * @Version: 1.0
     * @method: humpToLowerLine()
     * @param: [humpStr, UppercaseZeroAndLowercaseOne]
     * @return: java.lang.String
     */
    public static String humpToLowerLine(String humpStr, boolean ... defaultUppercaseAndTrueLowercase) {
        Matcher matcher = humpPattern.matcher(humpStr);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);

        //如果第二个形参为true 转为大写
        if (defaultUppercaseAndTrueLowercase.length>=1 && defaultUppercaseAndTrueLowercase[0]){
            return sb.toString().toUpperCase();
        }
        return sb.toString();
    }

    public static String underlineToHump (String str){
        //正则匹配下划线及后一个字符，删除下划线并将匹配的字符转成大写
        Matcher matcher = UNDERLINE_PATTERN.matcher(str);
        StringBuffer sb = new StringBuffer(str);
        if (matcher.find()) {
            sb = new StringBuffer();
            //将当前匹配的子串替换成指定字符串，并且将替换后的子串及之前到上次匹配的子串之后的字符串添加到StringBuffer对象中
            //正则之前的字符和被替换的字符
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
            //把之后的字符串也添加到StringBuffer对象中
            matcher.appendTail(sb);
        } else {
            //去除除字母之外的前面带的下划线
            return sb.toString().replaceAll("_", "");
        }
        return underlineToHump(sb.toString());
    }
}
