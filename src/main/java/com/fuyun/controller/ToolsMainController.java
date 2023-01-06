package com.fuyun.controller;

import com.fuyun.Application;
import com.fuyun.utils.PasswordUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.io.IOException;

/**
 * @describe: 工具触发方法
 * @Author: Cai_YF
 * @Date: 2022/1/29 11:53
 * @Version: v1.0
 */

public class ToolsMainController {

    @FXML
    private TabPane mainTabPane;

    @FXML
    private TextArea msgTextArea;


    // 初始化组件属性
    public void init() {
        // 初始化时间转换组件
        // this.dfComboBox.setItems(dateFormatList);
        // this.dfComboBox.setValue(dateFormatList.get(0));
        // // 初始赋值
        // this.timeStampText.setText(String.valueOf(System.currentTimeMillis()));
        // this.dateText.setText(sdf.format(new Date()));

        initListener();
    }


    // 初始化事件
    public void initListener(){
        // 添加tab
        FXMLLoader timeToolFxml = new FXMLLoader(Application.class.getResource("/fxml/time-tool.fxml"));
        FXMLLoader passwordToolFxml = new FXMLLoader(Application.class.getResource("/fxml/password-tool.fxml"));
        FXMLLoader postMainFxml = new FXMLLoader(Application.class.getResource("/fxml/post-main.fxml"));
        FXMLLoader conversionFxml = new FXMLLoader(Application.class.getResource("/fxml/conversion-tool.fxml"));
        try {
            Tab timeToolTab = new Tab("时间戳工具");
            timeToolTab.setContent(timeToolFxml.load());
            this.mainTabPane.getTabs().add(timeToolTab);
            Tab passwordToolTab = new Tab("随机密码工具");
            passwordToolTab.setContent(passwordToolFxml.load());
            this.mainTabPane.getTabs().add(passwordToolTab);
            Tab postMainTab = new Tab("postMain工具");
            postMainTab.setContent(postMainFxml.load());
            this.mainTabPane.getTabs().add(postMainTab);
            Tab conversionTab = new Tab("字符串转换工具");
            conversionTab.setContent(conversionFxml.load());
            this.mainTabPane.getTabs().add(conversionTab);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void showMessageDialog(String title, String message,Alert.AlertType type){
        // Alert alert = new Alert(type);
        // alert.setTitle(title);
        // alert.setHeaderText(head);
        // alert.setContentText(message);
        // alert.show();
        Alert alert = new Alert(type, message, new ButtonType[]{ButtonType.OK});
        alert.setTitle(title);
        alert.setHeaderText((String)null);
        alert.showAndWait();
    }

    public void showLog(Exception e){
        System.out.println(e);
        StackTraceElement[] ste = e.getStackTrace();
        StringBuilder esb = new StringBuilder();
        esb.append("Exception: ").append(e.getClass().getName()).append(": ").append(e.getMessage()).append("\n");
        for (int i = 0; i < 5 && i < ste.length; i++) {
            esb.append("                    ").append(ste[i].toString()).append("\n");
        }
        this.msgTextArea.setText(esb.toString());
    }
}
