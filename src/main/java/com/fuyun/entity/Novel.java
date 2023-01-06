package com.fuyun.entity;

import lombok.Data;

/**
 * @describe: 描述
 * @Author: Cai_YF
 * @Date: 2022/5/12 16:51
 * @Version: v1.0
 */
@Data
public class Novel {

    private String id;

    private String title;

    private String author;

    private String synopsis;

    private String detailUrl;

    private int xianCao;

    private int liangCao;

    private int gangCao;

    private int kuCao;

    private int duCao;


}
