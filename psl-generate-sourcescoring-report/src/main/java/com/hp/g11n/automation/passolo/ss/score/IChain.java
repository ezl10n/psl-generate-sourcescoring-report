package com.hp.g11n.automation.passolo.ss.score;


import com.hp.g11n.automation.passolo.ss.pojo.ReportData;

import java.util.List;

public interface IChain {
	boolean check(String source, String target);
	List<ReportData> gatherReport();
}
