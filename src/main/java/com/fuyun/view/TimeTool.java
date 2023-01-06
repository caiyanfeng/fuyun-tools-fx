package com.fuyun.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 * @describe: 时间转换工具
 * @Author: Cai_YF
 * @Date: 2022/1/30 16:08
 * @Version: v1.0
 */
public abstract class TimeTool implements Initializable {
    @FXML
    public ComboBox dfComboBox;
    @FXML
    public TextField timeStampText,dateText;
    @FXML
    public Button copyTimeBtn,copyDateBtn,getNowBtn,convertDateBtn,convertTimeBtn;
    @FXML
    public DatePicker datePicker;
}
