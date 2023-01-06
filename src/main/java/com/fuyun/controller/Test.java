package com.fuyun.controller;

import cn.hutool.http.Method;

import java.util.Random;

/**
 * @describe: 描述
 * @Author: Cai_YF
 * @Date: 2022/1/29 16:52
 * @Version: v1.0
 */
public class Test {

    private static final Random random = new Random();

    public static void main(String[] args) {
        Method m = Method.valueOf("GET");
        System.out.println(Method.valueOf("GET"));
    }
}
