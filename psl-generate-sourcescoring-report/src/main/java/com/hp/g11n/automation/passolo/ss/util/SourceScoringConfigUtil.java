package com.hp.g11n.automation.passolo.ss.util;

import com.hp.g11n.automation.core.utils.ReflectUtils;
import com.hp.g11n.automation.core.utils.TypeSafeConfigUtils;
import com.hp.g11n.automation.passolo.ss.score.IChain;
import com.typesafe.config.Config;
import org.reflections.Reflections;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by foy on 2016-08-08.
 */
public class SourceScoringConfigUtil {
    private static String CONFIG_DIR="D:/codes/HP/codes/ezl10n-ss/psl-generate-sourcescoring-report/psl-generate-sourcescoring-report/";
    private static String CONFIG_PATH= "/src/main/config/psl-generate-sourcescoring-report.conf";
    static Config config = TypeSafeConfigUtils.parse(Paths.get(CONFIG_DIR,CONFIG_PATH));
    private static final Reflections REFLECTIONS = ReflectUtils.buildReflections(
            "com.hp.g11n.automation.passolo.ss.score.impl"
    );

    public static Config getConfig(){
        return config;
    }
    public static Object getValue(String key){
        return config.getValue(key);
    }


    public static List<String> checkBoxs(){
        List<String> checkboxNames=new ArrayList<String>();
       REFLECTIONS.getSubTypesOf(IChain.class).stream().forEach( c -> checkboxNames.add(c.getSimpleName()));
        return checkboxNames;
    }

    public static List<Class> chainClassList(){
        List<Class> chains=new ArrayList<Class>();
        REFLECTIONS.getSubTypesOf(IChain.class).stream().forEach( c -> chains.add(c));
        return chains;
    }
}
