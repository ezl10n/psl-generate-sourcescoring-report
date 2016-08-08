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

    public SourceScoringPipelineManager() {
        checkChains = new ArrayList<>();
        if(log.isDebugEnabled()){
            log.debug("init ALL source scoring check Chains.");
        }
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
    public SourceScoringPipelineManager(List<Integer> rulesIndex){
        List<Class> list = SourceScoringConfigUtil.chainClassList();
        checkChains = new ArrayList<>();
        if(log.isDebugEnabled()){
            log.debug("init Selected source scoring check chains.");
        }
        rulesIndex.forEach( i -> {
            Class c= list.get(i);
            try {
                checkChains.add(((IChain) c.newInstance()));
            } catch (InstantiationException e) {
                log.error("can't instance IChain:" + c, e);
            } catch (IllegalAccessException e) {
                log.error("can't instance IChain:" + c, e);
            }
        });
    }

    @Override
    public String check(String key, String value) {
        Preconditions.checkNotNull(checkChains);
        Preconditions.checkArgument(checkChains.size() > 0, "checkChains should not be empty");
        checkChains.forEach( c -> c.check(key,value));
        return "OK";
    }

    @Override
    public List<ReportData> report() {
        List<ReportData> result=new ArrayList<>();
        Preconditions.checkNotNull(checkChains);
        Preconditions.checkArgument(checkChains.size() > 0, "checkChains should not be empty");
        checkChains.forEach(r -> {
            List<ReportData> list = r.gatherReport();
            if (list != null) {
                result.addAll(list);
            }
        });
        return result;
    }
}
