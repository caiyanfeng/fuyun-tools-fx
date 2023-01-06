package com.fuyun.controller;

import com.fuyun.handle.CopyBtnEventHandler;
import com.fuyun.view.TimeTool;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * @describe: 时间转换工具
 * @Author: Cai_YF
 * @Date: 2022/9/1 17:20
 * @Version: v1.0
 */
public class TimeToolController extends TimeTool {

    // 日期格式
    private ObservableList<String> dateFormatList = FXCollections.observableArrayList(
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd HH:mm:ss.SSS",
            "yyyy年MM月dd日HH时mm分ss秒",
            "yyyy-MM-dd","yyyy年MM月dd日"
    );
    private SimpleDateFormat sdf = new SimpleDateFormat(dateFormatList.get(0));



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initView();
        initListener();
    }

    // 初始化组件属性
    public void initView() {
        // 初始化时间转换组件
        this.dfComboBox.setItems(dateFormatList);
        this.dfComboBox.setValue(dateFormatList.get(0));
        // 初始赋值
        this.timeStampText.setText(String.valueOf(System.currentTimeMillis()));
        this.dateText.setText(sdf.format(new Date()));
    }

    // 初始化事件
    public void initListener(){

        // 初始化下拉组件选中事件
        this.dfComboBox.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue)->{
            sdf = new SimpleDateFormat((String) newValue);
        });

        this.copyTimeBtn.setOnAction(new CopyBtnEventHandler(this.timeStampText));
        this.copyDateBtn.setOnAction(new CopyBtnEventHandler(this.dateText));

    }

    // 获取当前时间
    public void getCurNow(){
        timeStampText.setText(String.valueOf(System.currentTimeMillis()));
        dateText.setText(sdf.format(new Date()));
    }

    public void convertTime(){
        try {
            long time = sdf.parse(dateText.getText()).getTime();
            timeStampText.setText(String.valueOf(time));
        } catch (ParseException e) {
            // showLog(e);
            // showMessageDialog("出现异常",e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void convertDate(){
        String date = sdf.format(new Date(Long.parseLong(timeStampText.getText())));
        dateText.setText(date);
    }
}
