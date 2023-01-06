package com.fuyun.controller;

import com.fuyun.view.RandomTool;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

/**
 * @describe: 描述
 * @Author: Cai_YF
 * @Date: 2022/1/30 16:44
 * @Version: v1.0
 */
public class RandomToolController extends RandomTool {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("--初始化");
    }

    public void generateUUID(ActionEvent actionEvent) {
        this.uuidResult.setText(String.valueOf(UUID.randomUUID()));
    }

    public void generateLowerCase(ActionEvent actionEvent){

    }

    public void generateUpperCase(ActionEvent actionEvent){

    }

    public void generateLetter(ActionEvent actionEvent){

    }

    public  void generateString(ActionEvent actionEvent){

    }

    public  void generateText(ActionEvent actionEvent){

    }


}
