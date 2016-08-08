package com.hp.g11n.automation.passolo.ss.score;


import com.hp.g11n.automation.passolo.ss.pojo.ReportData;

import java.util.List;

public interface ISourceScoring {
    String check(String key, String value);
    List<ReportData> report();
    static ISourceScoring getInstance(){
        return new SourceScoringPipelineManager();
    }
}
