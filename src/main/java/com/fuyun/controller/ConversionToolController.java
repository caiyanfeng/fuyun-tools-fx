package com.fuyun.controller;

import com.fuyun.handle.CopyBtnEventHandler;
import com.fuyun.utils.ConversionHumpUtil;
import com.fuyun.utils.EncodeUtil;
import com.fuyun.utils.PasswordUtil;
import com.fuyun.view.ConversionTool;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.ResourceBundle;

/**
 * @describe: 字符串转换
 * @Author: Cai_YF
 * @Date: 2022/10/28 17:01
 * @Version: v1.0
 */
public class ConversionToolController extends ConversionTool {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initListener();
    }

    private void initListener() {
        this.copyRetStrBtn.setOnAction(new CopyBtnEventHandler(this.retStrText));
        this.copyWaitStrBtn.setOnAction(new CopyBtnEventHandler(this.waitStrText));

        // 下滑线转驼峰
        this.lowerLineToHumpBtn.setOnAction(event -> {
            String lowerLineStr = this.waitStrText.getText();
            String humpStr = ConversionHumpUtil.lowerLineToHump(lowerLineStr);
            this.retStrText.setText(humpStr);
        });

        // 驼峰转下滑线
        this.humpToLowerLine.setOnAction(event -> {
            String humpStr = this.retStrText.getText();
            String lowerLineStr = ConversionHumpUtil.humpToLowerLine(humpStr);
            this.waitStrText.setText(lowerLineStr);
        });
        // 中文转Unicode
        this.unicodeEncodeBtn.setOnAction(event -> {
            String waitStr = this.waitStrText.getText();
            String retStr = EncodeUtil.unicodeEncode(waitStr);
            this.retStrText.setText(retStr);
        });
        // Unicode转中文
        this.unicodeDecodeBtn.setOnAction(event -> {
            String retStr = this.retStrText.getText();
            String waitStr = EncodeUtil.unicodeDecode(retStr);
            this.waitStrText.setText(waitStr);
        });
        // URLEncodeBtn
        this.URLEncodeBtn.setOnAction(event -> {
            String waitStr = this.waitStrText.getText();
            String retStr = null;
            try {
                retStr = URLEncoder.encode(waitStr,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            this.retStrText.setText(retStr);
        });
        // URLDecodeBtn
        this.URLDecodeBtn.setOnAction(event->{
            String retStr = this.retStrText.getText();
            String waitStr = null;
            try {
                waitStr = URLDecoder.decode(retStr,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            this.waitStrText.setText(waitStr);
        });
        // Base64EncodeBtn
        this.Base64EncodeBtn.setOnAction(event -> {
            String waitStr = this.waitStrText.getText();
            String retStr = Base64.getEncoder().encodeToString(waitStr.getBytes());
            this.retStrText.setText(retStr);
        });
        // Base64DecodeBtn
        this.Base64DecodeBtn.setOnAction(event->{
            String retStr = this.retStrText.getText();
            String waitStr = new String(Base64.getDecoder().decode(retStr));
            this.waitStrText.setText(waitStr);
        });
    }

}
