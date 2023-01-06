package com.fuyun.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fuyun.handle.AutoAddChangeListener;
import com.fuyun.handle.DelHBoxBtnHandler;
import com.fuyun.view.PostMainView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.HttpCookie;
import java.net.URL;
import java.util.*;

/**
 * @describe: 描述
 * @Author: Cai_YF
 * @Date: 2022/1/29 17:39
 * @Version: v1.0
 */
public class PostMainController extends PostMainView {

    @FXML
    private ToggleGroup toggleGroup = new ToggleGroup(),respBodyToggleGroup = new ToggleGroup();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initView();
        initListener();
    }

    public void initView(){
        // 单选组分配
        mapParamRadio.setToggleGroup(toggleGroup);
        jsonParamRadio.setToggleGroup(toggleGroup);
        mapParamRadio.setSelected(true); // 默认选中
        // 开关按钮分配
        defaultToggleButton.setToggleGroup(respBodyToggleGroup);
        jsonToggleButton.setToggleGroup(respBodyToggleGroup);
        defaultToggleButton.setSelected(true);
        // 下拉选项配置
        methodComboBox.getItems().addAll("GET","POST","PUT");
        methodComboBox.setValue("GET");
        // cookie表格标题行
        TableColumn<Map,Object> tableColumn1 = new TableColumn<>("Name");
        tableColumn1.setCellValueFactory(new MapValueFactory<>("Name"));
        cookieTableView.getColumns().add(tableColumn1);
        TableColumn<Map,Object> tableColumn2 = new TableColumn<>("Value");
        tableColumn2.setCellValueFactory(new MapValueFactory<>("Value"));
        cookieTableView.getColumns().add(tableColumn2);
        TableColumn<Map,Object> tableColumn3 = new TableColumn<>("Path");
        tableColumn3.setCellValueFactory(new MapValueFactory<>("Path"));
        cookieTableView.getColumns().add(tableColumn3);
        TableColumn<Map,Object> tableColumn4 = new TableColumn<>("Domain");
        tableColumn4.setCellValueFactory(new MapValueFactory<>("Domain"));
        cookieTableView.getColumns().add(tableColumn4);
    }

    private void initListener() {
        headKey1.textProperty().addListener(new AutoAddChangeListener(headKey1,headerVBox,headDelBtn1));
        headDelBtn1.setOnAction(new DelHBoxBtnHandler(headerVBox,headerHBox1));

        paramKey.textProperty().addListener(new AutoAddChangeListener(paramKey,paramVBox,paramDelBtn));
        paramDelBtn.setOnAction(new DelHBoxBtnHandler(paramVBox,paramHBox));

        methodComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            boolean isGet = newVal.equals("GET");
            // 如果是GET请求
            contentTypeRadioHBox.setVisible(!isGet);
            contentTypeRadioHBox.setManaged(!isGet);

        });

        // 单选按钮组，选中事件
        toggleGroup.selectedToggleProperty().addListener((obs,oldTg,newTg)->{
            RadioButton radioButton = (RadioButton) newTg;
            boolean isJson = radioButton.getText().equals("application/json");
            System.out.println(isJson);
            // 选中Json格式就显示多文本编辑
            jsonParamEditHBox.setVisible(isJson);
            jsonParamEditHBox.setManaged(isJson);
            mapParamHBox.setVisible(!isJson);
            mapParamHBox.setManaged(!isJson);
        });
        // 发送按钮触发
        sendBtn.setOnAction((event)->{
            String url = requestURLText.getText();
            // 请求头参数获取
            Map<String,Object> headMap = getRequestParamToMap(headerVBox);
            Map<String,Object> paramMap = new HashMap<>();
            // 请求参数获取
            // 首先判断jsonParamEditHBox是否显示，如果显示就获取这个
            if(jsonParamEditHBox.isVisible()){
                // JSON格式转换
                paramMap = JSON.parseObject(jsonParamTextArea.getText());
            }else{
                paramMap = getRequestParamToMap(paramVBox);
            }
            HttpRequest request = new HttpRequest(url);
            // 请求方式
            request.method(Method.valueOf(methodComboBox.getValue().toString()));
            request.form(paramMap);
            // 添加请求头
            for (Map.Entry<String, Object> entry : headMap.entrySet()) {
                request.header(entry.getKey(),entry.getValue().toString());
            }
            HttpResponse response = request.timeout(60000).execute();
            List<HttpCookie> cookieList = response.getCookies();
            // cookie表格赋值
            ObservableList<Map<String,Object>> items = FXCollections.<Map<String,Object>>observableArrayList();
            for (HttpCookie httpCookie : cookieList) {
                Map<String,Object> itemMap = new HashMap<>();
                itemMap.put("Name",httpCookie.getName());
                itemMap.put("Value",httpCookie.getValue());
                itemMap.put("Domain",httpCookie.getDomain());
                itemMap.put("Path",httpCookie.getPath());
                items.add(itemMap);
            }
            cookieTableView.getItems().addAll(items);
            respBodyTextArea.setText(response.body());
            // respHeadTextArea.setText();
            JSONObject jsonObject = new JSONObject();
            for (Map.Entry<String, List<String>> entry : response.headers().entrySet()) {
                jsonObject.put(entry.getKey(),entry.getValue());
            }
            respHeadTreeView.setRoot(parseJSON("respHead",jsonObject));
        });
        // 响应体格式按钮组选择事件
        respBodyToggleGroup.selectedToggleProperty().addListener((obs,oldTg,newTg)->{
            ToggleButton toggleButton = (ToggleButton) newTg;
            boolean isJson = toggleButton.getText().equals("JSON格式");
            respBodyJsonHBox.setVisible(isJson);
            respBodyJsonHBox.setManaged(isJson);
            respBodyTextHBox.setVisible(!isJson);
            respBodyTextHBox.setManaged(!isJson);
            if(isJson){
                JSONObject jsonObject = JSON.parseObject(respBodyTextArea.getText());
                respBodyTreeView.setRoot(parseJSON("respBody",jsonObject));
            }
        });
    }

    // 获取请求头
    private Map<String,Object> getRequestParamToMap(VBox vBox){
        Map<String,Object> map = new HashMap<>();
        for (Node child : vBox.getChildren()) {
            HBox hBox = (HBox) child;
            String key = null;
            int j = 0;
            for (Node hBoxChild : hBox.getChildren()) {
                if(hBoxChild.getClass().equals(TextField.class)){
                    TextField textField = (TextField) hBoxChild;
                    if(j++>0){
                        if(key!=null&&!key.equals("")){
                            map.put(key,textField.getText());
                        }
                    }else{
                        key = textField.getText();
                    }
                }
            }
        }
        return map;
    }

    private static TreeItem<String> parseJSON(String name, Object json) {
        TreeItem<String> item = new TreeItem<>();
        if (json instanceof JSONObject) {
            item.setValue(name);
            JSONObject object = (JSONObject) json;
            (object.entrySet()).forEach(entry -> {
                String childName = entry.getKey();
                Object childJson = entry.getValue();
                TreeItem<String> child = parseJSON(childName, childJson);
                item.getChildren().add(child);
            });
        } else if (json instanceof JSONArray) {
            item.setValue(name);
            JSONArray array = (JSONArray) json;
            for (int i = 0; i < array.size(); i++) {
                String childName = String.valueOf(i);
                Object childJson = array.get(i);
                TreeItem<String> child = parseJSON(childName, childJson);
                item.getChildren().add(child);
            }
        } else {
            item.setValue(name + " : " + json);
        }
        return item;
    }

}
