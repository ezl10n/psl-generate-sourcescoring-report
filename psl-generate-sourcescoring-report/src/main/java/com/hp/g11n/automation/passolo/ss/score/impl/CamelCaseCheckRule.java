package com.hp.g11n.automation.passolo.ss.score.impl;

import com.hp.g11n.automation.passolo.ss.pojo.ReportData;
import com.hp.g11n.automation.passolo.ss.score.IRule;
import com.hp.g11n.automation.passolo.ss.score.annotation.RuleData;
import com.hp.g11n.automation.passolo.ss.util.Constant;

import java.util.List;
@RuleData(id="CamelCaseCheckRule",name=Constant.CAMELCASE,order=2,ruleClass = CamelCaseCheckRule.class)
public class CamelCaseCheckRule implements IRule{

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
