package com.hp.g11n.automation.passolo.ss.output;

import java.util.List;

import com.hp.g11n.automation.passolo.ss.pojo.ReportData;

public interface ISSOutput {
	public List<ReportData> report(List<ReportData> lst,String id,String value);
}
