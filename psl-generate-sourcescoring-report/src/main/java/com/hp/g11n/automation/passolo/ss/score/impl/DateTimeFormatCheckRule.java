package com.hp.g11n.automation.passolo.ss.score.impl;

import com.hp.g11n.automation.passolo.ss.pojo.ReportData;
import com.hp.g11n.automation.passolo.ss.score.IRule;
import com.hp.g11n.automation.passolo.ss.score.annotation.RuleData;
import com.hp.g11n.automation.passolo.ss.util.Constant;

import java.util.List;
@RuleData(id="DateTimeFormatCheckRule",name=Constant.DATETIMEFORMAT,order=3,ruleClass = DateTimeFormatCheckRule.class)
public class DateTimeFormatCheckRule implements IRule{

	@Override
	public boolean check(String source, String target) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ReportData> gatherReport() {
		return null;
	}

}
