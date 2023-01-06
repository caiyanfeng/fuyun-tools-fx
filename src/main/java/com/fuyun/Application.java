package com.fuyun;

import com.fuyun.controller.ToolsMainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        // fxml页面
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/fxml/tools-main.fxml"));
        // 生成面板大小
        Scene scene = new Scene(fxmlLoader.load(), 680, 530);
        // 标题
        stage.setTitle("浮云--小工具");
        stage.setResizable(true); // 开启窗口缩放
        stage.setScene(scene);
        ToolsMainController controller = fxmlLoader.getController();   //获取Controller的实例对象
        //Controller中写的初始化方法
        controller.init();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}