package com.fuyun.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * @describe: 随机密码工具
 * @Author: Cai_YF
 * @Date: 2022/9/2 9:45
 * @Version: v1.0
 */
public abstract class PasswordTool implements Initializable {

    @FXML
    public TextField psLenTextField,psText,psText1,psText2;
    @FXML
    public Button createPassWordBtn,copyPsBtn,copyPsBtn1,copyPsBtn2;

    @FXML
    public CheckBox numCheckBox,upperCheckBox,lowerCheckBox,specCheckBox;

}
