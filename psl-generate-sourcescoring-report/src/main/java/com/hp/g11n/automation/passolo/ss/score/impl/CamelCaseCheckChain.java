package com.hp.g11n.automation.passolo.ss.score.impl;

import com.hp.g11n.automation.passolo.ss.pojo.ReportData;
import com.hp.g11n.automation.passolo.ss.score.IChain;
import com.hp.g11n.automation.passolo.ss.score.annotation.ChainData;

import java.util.List;
@ChainData(id="CamelCaseCheckChain",name="CamelCase",order=2,chainClass = CamelCaseCheckChain.class)
public class CamelCaseCheckChain implements IChain{

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
