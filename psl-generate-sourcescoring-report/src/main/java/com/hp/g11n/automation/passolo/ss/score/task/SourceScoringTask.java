package com.hp.g11n.automation.passolo.ss.score.task;

import java.io.FileWriter;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javafx.concurrent.Task;
import javafx.scene.control.TextArea;

import com.hp.g11n.automation.passolo.ss.input.impl.SSInputImpl;
import com.hp.g11n.automation.passolo.ss.score.impl.CamelCaseCheckChain;
import com.hp.g11n.automation.passolo.ss.score.impl.ConcatenationCheckChain;
import com.hp.g11n.automation.passolo.ss.score.impl.DateTimeFormatCheckChain;
import com.hp.g11n.automation.passolo.ss.score.impl.VariablesCheckChain;
import com.hp.g11n.automation.passolo.ss.util.Constant;
import com.hp.g11n.automation.passolo.ss.util.FileUtil;
import com.hp.g11n.sdl.psl.interop.core.IPassoloApp;
import com.hp.g11n.sdl.psl.interop.core.IPslSourceList;
import com.hp.g11n.sdl.psl.interop.core.IPslSourceLists;
import com.hp.g11n.sdl.psl.interop.core.IPslSourceString;

public class SourceScoringTask extends Task<Void> {
	static String FILE_PATCH = "C:\\SourceScoring\\PatternConfig.xlsx";
	private Set<Entry<String, String>> entries = null;
	IPslSourceLists sourceLists = null;
	private String source;
	private String report;
	private TextArea log;
	private List<String>lstSubTask;

	public void setUp(String sourceDir, String reportDir, TextArea ta,List<String>lstSubTask) {
		this.source = sourceDir;
		this.report = reportDir;
		this.log = ta;
		this.lstSubTask = lstSubTask;
	}

	@Override
	protected Void call() throws Exception {
		int endnum = 50;
		// output
		FileWriter fw = new FileWriter(report);

		SSInputImpl ssii = new SSInputImpl();
		IPslSourceLists sourceLists = ssii.getSourceLists(source);
		CamelCaseCheckChain camelCaseCheckChain = new CamelCaseCheckChain();
		ConcatenationCheckChain concatenationCheckChain = new ConcatenationCheckChain();
		DateTimeFormatCheckChain dateTimeFormatCheckChain = new DateTimeFormatCheckChain();
		VariablesCheckChain variablesCheckChain = new VariablesCheckChain();
		FileUtil fu = new FileUtil();
		System.out.println("1111111111");
		entries = (Set<Entry<String, String>>)fu.readExcelFile(FILE_PATCH).entrySet();
		System.out.println("22222222222");
		outer: for (IPslSourceList sourceList : sourceLists.toList()) {
			System.out.println("33333333333");
			log.appendText("proccessing start.........");
			//iterator this SourceString
			for (IPslSourceString sourceString : sourceList.getSourceStrings()) {
				//iterator the rule which from the UI checkBoxes
				for(String doTask:lstSubTask){
					for (Entry<String, String> entry : entries) {  
						if(entry.getKey().startsWith(doTask)){
							if (sourceString == null 
									|| sourceString.getText()==null 
									||sourceString.getText().toLowerCase().trim().isEmpty()) {
								continue;
							}
							String source = sourceString.getText().toLowerCase().trim();
							for (String k : entry.getValue().split(",")) {
								if(doTask.equals(Constant.CONCATENATION)){
									if(concatenationCheckChain.check(source, k)){
//										log.appendText("find one match key/value:"+ sourceString.getID() + " " + sourceString.getText() + "\n");
										fw.write(sourceString.getID() + ","+ sourceString.getText() + "\n");
									}
								}
								if(doTask.equals(Constant.CAMELCASE)){
									if(camelCaseCheckChain.check(source, k)){
//										log.appendText("find one match key/value:"+ sourceString.getID() + " " + sourceString.getText() + "\n");
										fw.write(sourceString.getID() + ","+ sourceString.getText() + "\n");
									}
								}
								if(doTask.equals(Constant.DATETIMEFORMAT)){
									if(dateTimeFormatCheckChain.check(source, k)){
//										log.appendText("find one match key/value:"+ sourceString.getID() + " " + sourceString.getText() + "\n");
										fw.write(sourceString.getID() + ","+ sourceString.getText() + "\n");
									}
								}
								if(doTask.equals(Constant.VARIABLEST)){
									if(variablesCheckChain.check(source, k)){
//										log.appendText("find one match key/value:"+ sourceString.getID() + " " + sourceString.getText() + "\n");
										fw.write(sourceString.getID() + ","+ sourceString.getText() + "\n");
									}
								}
								
							}
						}
					}
				}
			}
			break;
		}

		log.appendText("process source lists done...\n");

		fw.close();

		// Close the project.
//		project.close();
		// Shut down passolo instance.
		IPassoloApp.quit();
		return null;
	}
}
