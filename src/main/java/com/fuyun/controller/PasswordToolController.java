package com.fuyun.controller;

import com.fuyun.handle.CopyBtnEventHandler;
import com.fuyun.utils.PasswordUtil;
import com.fuyun.view.PasswordTool;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @describe: 描述
 * @Author: Cai_YF
 * @Date: 2022/9/2 9:47
 * @Version: v1.0
 */
public class PasswordToolController extends PasswordTool {


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 随机密码初始化
        PasswordUtil.checked(true,0,"num");
        PasswordUtil.checked(true,1,"char");
        PasswordUtil.checked(true,2,"CHAR");

        this.numCheckBox.selectedProperty().addListener((observable,oldValue,newValue)->{
            if(!newValue&& PasswordUtil.listStr.size()==1){
                // 已经剩下最后一个选项了，不能再取消选中
                numCheckBox.setSelected(true);
                return;
            }
            PasswordUtil.checked(newValue,0,"num");
        });
        this.lowerCheckBox.selectedProperty().addListener((observable,oldValue,newValue)->{
            if(!newValue&&PasswordUtil.listStr.size()==1){
                // 已经剩下最后一个选项了，不能再取消选中
                lowerCheckBox.setSelected(true);
                return;
            }
            PasswordUtil.checked(newValue,1,"char");
        });
        this.upperCheckBox.selectedProperty().addListener((observable,oldValue,newValue)->{
            if(!newValue&&PasswordUtil.listStr.size()==1){
                // 已经剩下最后一个选项了，不能再取消选中
                upperCheckBox.setSelected(true);
                return;
            }
            PasswordUtil.checked(newValue,2,"CHAR");
        });
        this.specCheckBox.selectedProperty().addListener((observable,oldValue,newValue)->{
            if(!newValue&&PasswordUtil.listStr.size()==1){
                // 已经剩下最后一个选项了，不能再取消选中
                specCheckBox.setSelected(true);
                return;
            }
            PasswordUtil.checked(newValue,3,"spec");
        });

        psLenTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            String str = newValue.replaceAll("[^\\d]","");
            if(str!=null&&!str.equals("")&&Integer.valueOf(str)>100){
                // showMessageDialog("提示","长度最好不要超过100哈！", Alert.AlertType.INFORMATION);
            }
            psLenTextField.setText(str);
        });

        this.createPassWordBtn.setOnAction(event -> {
            String length = this.psLenTextField.getText();
            if(length!=null&&!length.equals("")){
                if(Integer.valueOf(length)>100){
                    // showMessageDialog("提示","长度最好不要超过100哈！", Alert.AlertType.INFORMATION);
                    return;
                }
                // 生成密码
                String password = PasswordUtil.getPassword(Integer.valueOf(length));
                psText.setText(password);
                String password1 = PasswordUtil.getPassword(Integer.valueOf(length));
                psText1.setText(password1);
                String password2 = PasswordUtil.getPassword(Integer.valueOf(length));
                psText2.setText(password2);
            }
        });

        this.copyPsBtn.setOnAction(new CopyBtnEventHandler(this.psText));
        this.copyPsBtn1.setOnAction(new CopyBtnEventHandler(this.psText1));
        this.copyPsBtn2.setOnAction(new CopyBtnEventHandler(this.psText2));
    }

}
