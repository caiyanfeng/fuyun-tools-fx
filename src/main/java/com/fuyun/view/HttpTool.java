package com.fuyun.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebView;

import java.util.Map;

/**
 * @describe: 描述
 * @Author: Cai_YF
 * @Date: 2022/9/1 16:53
 * @Version: v1.0
 */
public abstract class HttpTool implements Initializable {
    @FXML
    protected TextField urlTextField;
    @FXML
    protected ChoiceBox<String> methodChoiceBox;
    @FXML
    protected Button sendButton;
    @FXML
    protected Button toBrowerButton;
    @FXML
    protected CheckBox paramsDataCheckBox;
    @FXML
    private Button addParamsDataButton;
    @FXML
    protected CheckBox paramsDataIsStringCheckBox;
    @FXML
    protected TextArea paramsDataTextArea;
    @FXML
    protected TableView<Map<String, String>> paramsDataTableView;
    @FXML
    protected TableColumn<Map<String, String>, String> paramsDataNameTableColumn;
    @FXML
    protected TableColumn<Map<String, String>, String> paramsDataValueTableColumn;
    @FXML
    protected TableColumn<Map<String, String>, String> paramsDataRemarkTableColumn;
    @FXML
    protected CheckBox paramsHeaderCheckBox;
    @FXML
    private Button addParamsHeaderButton;
    @FXML
    protected TableView<Map<String, String>> paramsHeaderTableView;
    @FXML
    protected TableColumn<Map<String, String>, String> paramsHeaderNameTableColumn;
    @FXML
    protected TableColumn<Map<String, String>, String> paramsHeaderValueTableColumn;
    @FXML
    protected TableColumn<Map<String, String>, String> paramsHeaderRemarkTableColumn;
    @FXML
    protected CheckBox paramsCookieCheckBox;
    @FXML
    private Button addParamsCookieButton;
    @FXML
    protected TableView<Map<String, String>> paramsCookieTableView;
    @FXML
    protected TableColumn<Map<String, String>, String> paramsCookieNameTableColumn;
    @FXML
    protected TableColumn<Map<String, String>, String> paramsCookieValueTableColumn;
    @FXML
    protected TableColumn<Map<String, String>, String> paramsCookieRemarkTableColumn;
    @FXML
    protected TextArea ResponseBodyTextArea;
    @FXML
    protected TextArea ResponseHeaderTextArea;
    @FXML
    protected WebView ResponseHtmlWebView;
    @FXML
    protected ImageView ResponseImgImageView;
}
