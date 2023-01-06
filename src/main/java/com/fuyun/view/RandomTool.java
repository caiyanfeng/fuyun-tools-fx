package com.fuyun.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 * @describe: 描述
 * @Author: Cai_YF
 * @Date: 2022/1/30 16:12
 * @Version: v1.0
 */
public abstract class RandomTool implements Initializable {

    @FXML
    protected TextField uuidResult;

    @FXML
    protected TextField lowerCaseLength;

    @FXML
    protected TextField lowerCaseResult;

    @FXML
    protected TextField upperCaseLength;

    @FXML
    protected TextField upperCaseResult;

    @FXML
    protected TextField letterLength;

    @FXML
    protected TextField letterResult;

    @FXML
    protected TextField stringLength;

    @FXML
    protected TextField stringResult;

    @FXML
    protected TextField textLength;

    @FXML
    protected TextField textResult;

    public abstract void generateUUID(ActionEvent actionEvent);

    public abstract void generateLowerCase(ActionEvent actionEvent);

    public abstract void generateUpperCase(ActionEvent actionEvent);

    public abstract void generateLetter(ActionEvent actionEvent);

    public abstract void generateString(ActionEvent actionEvent);

    public abstract void generateText(ActionEvent actionEvent);
}
