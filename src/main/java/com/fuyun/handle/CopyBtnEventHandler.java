package com.fuyun.handle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

/**
 * @describe: 复制按钮点击事件触发
 * @Author: Cai_YF
 * @Date: 2022/1/29 14:52
 * @Version: v1.0
 */
public class CopyBtnEventHandler implements EventHandler {

    private TextInputControl textField;

    public CopyBtnEventHandler(TextInputControl textField) {
        this.textField = textField;
    }

    @Override
    public void handle(Event event) {
        // 根据传进来的textField获取内容，复制到系统剪切板
        String str = textField.getText();
        StringSelection stringSelection = new StringSelection(str);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
    }
}
