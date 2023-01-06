package com.fuyun.handle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @describe: 删除HBox按钮的事件
 * @Author: Cai_YF
 * @Date: 2022/1/30 12:29
 * @Version: v1.0
 */
public class DelHBoxBtnHandler implements EventHandler {

    private VBox parentVBox;

    private HBox removeHBox;

    public DelHBoxBtnHandler(VBox parentVBox,HBox removeHBox){
        this.parentVBox = parentVBox;
        this.removeHBox = removeHBox;
    }

    @Override
    public void handle(Event event) {
        parentVBox.getChildren().remove(removeHBox);
    }
}
