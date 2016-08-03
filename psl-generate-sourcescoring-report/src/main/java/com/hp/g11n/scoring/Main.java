package com.hp.g11n.scoring;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import com.hp.g11n.automation.passolo.readfile.ReadFile;
import com.hp.g11n.automation.passolo.tasks.SourceScoringTask;

public class Main extends Application {
	// the path of file
	static String  FILE_PATCH = "C:\\SourceScoring\\Category.txt";
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Region veil = new Region();
		veil.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4)");

		primaryStage.setTitle("Source Scoring");
		Group root = new Group();
		Scene scene = new Scene(root, 850, 400);
		primaryStage.setScene(scene);

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		scene.setRoot(grid);

		// FileChooser
		DirectoryChooser chooser = new DirectoryChooser();

		// source file
		Label label1 = new Label("File to be processed:*:");
		Button browser1 = new Button("Browse");

		final TextField sourceFile = new TextField();
		sourceFile.setText("c:/tmp/psl-generate-sorucescoring-report");
		browser1.setOnAction((final ActionEvent e) -> {

			File file = chooser.showDialog(primaryStage);
			if (file != null) {
				sourceFile.setText(file.getAbsolutePath());
			}
		});
		GridPane.setConstraints(label1, 0, 0);
		GridPane.setConstraints(sourceFile, 1, 0);
		GridPane.setConstraints(browser1, 2, 0);
		grid.getChildren().addAll(label1, sourceFile, browser1);

		// exclusion file path
		Label label2 = new Label("Exclusion File Path:");
		Button browser2 = new Button("Browse");
		final TextField exclusionFile = new TextField();
		browser2.setOnAction((final ActionEvent e) -> {
			File file = chooser.showDialog(primaryStage);
			if (file != null) {
				exclusionFile.setText(file.getAbsolutePath());
			}
		});
		GridPane.setConstraints(label2, 0, 1);
		GridPane.setConstraints(exclusionFile, 1, 1);
		GridPane.setConstraints(browser2, 2, 1);
		grid.getChildren().addAll(label2, exclusionFile, browser2);

		// output folder
		Label label3 = new Label("Write Output to the folder:");
		Button browser3 = new Button("Browse");
		final TextField outputFolder = new TextField();
		outputFolder.setText("c:/tmp/psl-generate-sorucescoring-report");
		browser3.setOnAction((final ActionEvent e) -> {
			File file = chooser.showDialog(primaryStage);
			if (file != null) {
				outputFolder.setText(file.getAbsolutePath());
			}
		});
		GridPane.setConstraints(label3, 0, 2);
		GridPane.setConstraints(outputFolder, 1, 2);
		GridPane.setConstraints(browser3, 2, 2);
		grid.getChildren().addAll(label3, outputFolder, browser3);
		
		
		//initialization check boxes
		ReadFile rf = new ReadFile();
		List<String> lstValue= rf.readTxtFile(FILE_PATCH);
		int forcount=0;
		int startEnd =2;
		List<CheckBox> lstCheckBox = new ArrayList<CheckBox>();
		if(lstValue != null && lstValue.size()>0){
			for(int i =0;i<lstValue.size();i++){
				String value = lstValue.get(i);
				CheckBox cb= new CheckBox(value);
				GridPane.setConstraints(cb, forcount, startEnd+1);
				grid.getChildren().add(cb);
				lstCheckBox.add(cb);
				forcount++;
				if(forcount ==4){
					forcount=0;
					startEnd = startEnd +1;
				}
			}
		}
		startEnd =2;
		if(lstValue.size()%4==0){
			startEnd = startEnd +lstValue.size()/4+1;
		}
		if(lstValue.size()%4>0){
			startEnd = startEnd +lstValue.size()/4+2;
		}
		// button
		Button submit = new Button("Run");

		GridPane.setConstraints(submit, 1, startEnd);
		grid.getChildren().add(submit);

		Button clear = new Button("Cancel");
		GridPane.setConstraints(clear, 2, startEnd);
		grid.getChildren().add(clear);

		// log textArea
		Label label4 = new Label("logs:");
		TextArea logArea = new TextArea();
		logArea.appendText("log will display here!\n");
		GridPane.setConstraints(label4, 0, startEnd+2);
		GridPane.setConstraints(logArea, 1, startEnd+2);
		grid.getChildren().addAll(label4, logArea);

		primaryStage.show();
		SourceScoringTask task = new SourceScoringTask();
		List<String> lstSubTask = new ArrayList<String>();
		submit.setOnAction(event -> {
			if(lstCheckBox.size()>0){
				for(int k=0;k<lstCheckBox.size();k++){
					CheckBox cb = lstCheckBox.get(k);
					if(cb.isSelected()){
						lstSubTask.add(cb.getText());
					}
				}
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
			task.setUp(sourceFile.getText(),
					outputFolder.getText() + "/" + sdf.format(new Date())
							+ ".csv", logArea,lstSubTask);
			new Thread(task).start();
		});

	}
}
