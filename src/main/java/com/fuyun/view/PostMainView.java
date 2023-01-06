package com.fuyun.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @describe: 描述
 * @Author: Cai_YF
 * @Date: 2022/9/1 17:11
 * @Version: v1.0
 */
public abstract class PostMainView implements Initializable {
    @FXML
    public VBox headerVBox,paramVBox;

    @FXML
    public HBox headerHBox1,paramHBox,contentTypeRadioHBox,jsonParamEditHBox,mapParamHBox,respBodyTextHBox,respBodyJsonHBox;

    @FXML
    public ComboBox methodComboBox;

    @FXML
    public TextField requestURLText,headKey1,headValue1,paramKey,paramValue;

    @FXML
    public Button headDelBtn1,paramDelBtn,sendBtn;

    @FXML
    public RadioButton mapParamRadio,jsonParamRadio;

    @FXML
    public TextArea jsonParamTextArea,respBodyTextArea,respHeadTextArea;

    @FXML
    public ScrollPane respHeadScrollPane;

    @FXML
    public TreeView respHeadTreeView,respBodyTreeView;

    @FXML
    public ToggleButton defaultToggleButton,jsonToggleButton;

    @FXML
    public TableView cookieTableView;
}
