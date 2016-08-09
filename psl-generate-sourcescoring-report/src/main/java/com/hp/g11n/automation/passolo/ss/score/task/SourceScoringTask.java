package com.hp.g11n.automation.passolo.ss.score.task;

import com.hp.g11n.automation.passolo.ss.pojo.ReportData;
import com.hp.g11n.automation.passolo.ss.score.ISourceScoring;
import com.hp.g11n.automation.passolo.ss.util.PassoloTemplate;
import com.hp.g11n.sdl.psl.interop.core.IPslSourceList;
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
	private List<Integer> rulesCheckedIdx;

	public void setUp(String sourceDir, String reportDir,List<Integer> rulesCheckedIdx) {
		this.source = sourceDir;
		this.report = reportDir;
		this.rulesCheckedIdx = rulesCheckedIdx;
	}

	@Override
	protected Void call() throws Exception {

		// output
		final FileWriter fw = new FileWriter(report);

		//init
		ISourceScoring checkReport = (rulesCheckedIdx == null || rulesCheckedIdx.size() < 1) ?
				ISourceScoring.getInstance():ISourceScoring.getInstance(rulesCheckedIdx);
		PassoloTemplate.build(source).process((p,sourceLists) -> {
			int progress=0;
			for (IPslSourceList sourceList : sourceLists.toList()) {
				//iterator this SourceString
				for (IPslSourceString sourceString : sourceList.getSourceStrings()) {
					//iterator the rule which from the UI checkBoxes
					checkReport.check(sourceString.getID(),sourceString.getText());
				}
				progress++;
				this.updateProgress(progress,sourceLists.getCount());
			}

		});

		//report
		List<ReportData> report = checkReport.report();
		report.forEach( r -> {
			try {
				fw.write(r.getId()+","+r.getValue()+"\n");
			} catch (IOException e) {
				log.error("write report CSV failure.",e);
			}

		});

		fw.close();

		return null;
	}
}
