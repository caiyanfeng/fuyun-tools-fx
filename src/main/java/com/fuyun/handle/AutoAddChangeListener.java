package com.fuyun.handle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


/**
 * @describe: 输入框改变时触发自动添加事件
 * @Author: Cai_YF
 * @Date: 2022/1/30 11:57
 * @Version: v1.0
 */
public class AutoAddChangeListener implements ChangeListener<String> {

    private TextField textField;

    private VBox parentVBox;

    private Button delBtn;

    public AutoAddChangeListener(TextField textField, VBox parentVBox, Button delBtn){
        this.textField = textField;
        this.parentVBox = parentVBox;
        this.delBtn = delBtn;
    }
    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        System.out.println("---触发输入框改变事件---");
        // 显示按钮
        delBtn.setVisible(true);
        // 添加HBox
        HBox hBox = new HBox();
        Button addDelBtn = new Button();
        addDelBtn.setText("del");
        addDelBtn.setVisible(false);
        addDelBtn.setOnAction(new DelHBoxBtnHandler(parentVBox,hBox));
        TextField key = new TextField();
        key.setPrefWidth(198);
        key.textProperty().addListener(new AutoAddChangeListener(key,parentVBox,addDelBtn));
        hBox.getChildren().add(key);
        TextField value = new TextField();
        value.setPrefWidth(309);
        hBox.getChildren().add(value);
        hBox.getChildren().add(addDelBtn);
        // 样式
        hBox.setSpacing(10.0);
        hBox.setPadding(new Insets(2,10,2,10));
        parentVBox.getChildren().add(hBox);
        parentVBox.getParent().getParent().getParent().prefHeight(500);
        // 设置自适应
        HBox.setHgrow(key, Priority.ALWAYS);
        HBox.setHgrow(value, Priority.ALWAYS);
        HBox.setHgrow(addDelBtn, Priority.ALWAYS);
        VBox.setVgrow(hBox,Priority.NEVER);
        // 触发后删除监听
        textField.textProperty().removeListener(this);
    }
}
