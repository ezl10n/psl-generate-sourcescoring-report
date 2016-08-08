package com.hp.g11n.automation.passolo.ss.control;

import com.hp.g11n.automation.passolo.ss.score.task.SourceScoringTask;
import com.hp.g11n.automation.passolo.ss.util.SourceScoringConfigUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by foy on 2016-08-05.
 */

public class MainViewController implements Initializable {

    @FXML
    private Parent root;

    @FXML
    private TextField sourceUrl;
    @FXML
    private TextField exclusionPattern;

    @FXML
    private TextField outputUrl;

    @FXML
    private HBox checkRules;

    private DirectoryChooser chooser;

    private FileChooser fileChooser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(chooser == null){
            chooser = new DirectoryChooser();
        }
        if(fileChooser == null){
            fileChooser=new FileChooser();
        }
        SourceScoringConfigUtil.checkBoxs().forEach(checkBoxValue -> {
            checkRules.getChildren().add(new CheckBox(checkBoxValue));
        });
    }

    @FXML
    public void chooseSource(ActionEvent event){
        File file = fileChooser.showOpenDialog(root.getScene().getWindow());
        if (file != null) {
            sourceUrl.setText(file.getAbsolutePath());
        }
    }
    @FXML
    public void chooseExclusion(ActionEvent event){
        File file = chooser.showDialog(root.getScene().getWindow());
        if (file != null) {
            exclusionPattern.setText(file.getAbsolutePath());
        }
    }
    @FXML
    public void chooseOutput(ActionEvent event){
        File file = chooser.showDialog(root.getScene().getWindow());
        if (file != null) {
            outputUrl.setText(file.getAbsolutePath());
        }
    }

    @FXML
    public void scoring(ActionEvent event){
        List<Integer> rules=new ArrayList<Integer>();
        for(int i=0;i < checkRules.getChildren().size();i++){
            CheckBox cb=(CheckBox)checkRules.getChildren().get(i);
            if(cb.isSelected()){
                rules.add(i);
            }
        }
        SourceScoringTask task = new SourceScoringTask();
        task.setUp(sourceUrl.getText(),outputUrl.getText()+"/"+System.currentTimeMillis()+".csv",rules);
        new Thread(task).start();
    }
}
