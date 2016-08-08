package com.hp.g11n.automation.passolo.ss.score;


import com.google.common.base.Preconditions;
import com.hp.g11n.automation.passolo.ss.pojo.ReportData;
import com.hp.g11n.automation.passolo.ss.util.SourceScoringConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SourceScoringPipelineManager implements ISourceScoring{
    private final Logger log = LoggerFactory.getLogger(getClass());
    List<IChain> checkChains;

    SourceScoringPipelineManager() {
        List<Class> list = SourceScoringConfigUtil.chainClassList();
        for (Class c : list) {
            try {
                checkChains.add(((IChain) c.newInstance()));
            } catch (InstantiationException e) {
                log.error("can't instance IChain:" + c, e);
            } catch (IllegalAccessException e) {
                log.error("can't instance IChain:" + c, e);
            }
        }
    }

    @Override
    public String check(String key, String value) {
        Preconditions.checkNotNull(checkChains);
        checkChains.forEach( c -> c.check(key,value));
        return "OK";
    }

    @Override
    public List<ReportData> report() {
        List<ReportData> result=new ArrayList<>();
        Preconditions.checkNotNull(checkChains);
        checkChains.forEach(r -> result.addAll(r.gatherReport()));
        return result;
    }
}
