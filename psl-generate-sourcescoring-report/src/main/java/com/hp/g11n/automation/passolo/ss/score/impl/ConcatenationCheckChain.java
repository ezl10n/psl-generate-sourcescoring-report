package com.hp.g11n.automation.passolo.ss.score.impl;

import com.google.common.base.Preconditions;
import com.hp.g11n.automation.passolo.ss.pojo.ReportData;
import com.hp.g11n.automation.passolo.ss.score.IChain;
import com.hp.g11n.automation.passolo.ss.util.SourceScoringConfigUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @Descripation 根据传入的源数据和匹配目标检查
 * @CreatedBy: Ali Cao
 * @Date: 2016年8月5日
 * @Time: 上午10:19:16
 *
 */
public class ConcatenationCheckChain implements IChain{
private static final String KEY_WORDS="psl.psl-generate-sourcescoring-report.concatenation.key-words";
	private List<String> keywords;
	private final List<ReportData> report = new ArrayList<>();

	public ConcatenationCheckChain(){
		keywords = SourceScoringConfigUtil.getConfig().getStringList(KEY_WORDS);
	}

	@Override
	public boolean check(String key, String value) {
		Preconditions.checkNotNull(keywords);
		Preconditions.checkNotNull(value);
		for(String v : keywords) {
			if (value.startsWith(v) || value.endsWith(v)) {
				report.add(new ReportData(key, value));
				return true;
			}
		}
		return false;
	}

	@Override
	public List<ReportData> gatherReport() {
		return report;
	}

}
