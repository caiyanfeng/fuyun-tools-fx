package com.fuyun.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * @describe: 转换工具类
 * @Author: Cai_YF
 * @Date: 2022/10/28 16:53
 * @Version: v1.0
 */
public abstract class ConversionTool implements Initializable {

    @FXML
    public Button copyWaitStrBtn,copyRetStrBtn,lowerLineToHumpBtn,humpToLowerLine,unicodeEncodeBtn,unicodeDecodeBtn,
            URLEncodeBtn,URLDecodeBtn,Base64EncodeBtn,Base64DecodeBtn;
    @FXML
    public TextArea waitStrText,retStrText;

}
