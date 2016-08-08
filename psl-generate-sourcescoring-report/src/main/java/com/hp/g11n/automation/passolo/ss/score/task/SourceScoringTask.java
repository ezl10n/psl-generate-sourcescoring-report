package com.hp.g11n.automation.passolo.ss.score.task;

import com.hp.g11n.automation.passolo.ss.pojo.ReportData;
import com.hp.g11n.automation.passolo.ss.score.ISourceScoring;
import com.hp.g11n.automation.passolo.ss.util.FileUtil;
import com.hp.g11n.sdl.psl.interop.core.IPassoloApp;
import com.hp.g11n.sdl.psl.interop.core.IPslSourceList;
import com.hp.g11n.sdl.psl.interop.core.IPslSourceLists;
import com.hp.g11n.sdl.psl.interop.core.IPslSourceString;
import javafx.concurrent.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SourceScoringTask extends Task<Void> {
	private final Logger log = LoggerFactory.getLogger(getClass());
	private String source;
	private String report;
	private List<String>lstSubTask;

	public void setUp(String sourceDir, String reportDir,List<String>lstSubTask) {
		this.source = sourceDir;
		this.report = reportDir;
		this.lstSubTask = lstSubTask;
	}

	@Override
	protected Void call() throws Exception {
		int endnum = 50;
		// output
		final FileWriter fw = new FileWriter(report);

		IPslSourceLists sourceLists = FileUtil.getSourceLists(source);
		ISourceScoring checkReport=ISourceScoring.getInstance();
		outer:
		for (IPslSourceList sourceList : sourceLists.toList()) {
			//iterator this SourceString
			for (IPslSourceString sourceString : sourceList.getSourceStrings()) {
				//iterator the rule which from the UI checkBoxes
				checkReport.check(sourceString.getID(),sourceString.getText());
				endnum--;
				//just process endnum sourceString.
				if(endnum < 0 ){
					break outer;
				}
			}
		}

		List<ReportData> report = checkReport.report();
		report.forEach( r -> {
			try {
				fw.write(r.getId()+","+r.getValue()+"\n");
			} catch (IOException e) {
				log.error("write report CSV failure.",e);
			}

		});

		fw.close();

		// Close the project.
		// Shut down passolo instance.
		IPassoloApp.quit();
		return null;
	}
}
