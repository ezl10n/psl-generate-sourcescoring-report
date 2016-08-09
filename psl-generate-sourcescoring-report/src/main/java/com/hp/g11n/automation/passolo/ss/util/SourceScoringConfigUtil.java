package com.hp.g11n.automation.passolo.ss.util;

import com.hp.g11n.automation.core.utils.ReflectUtils;
import com.hp.g11n.automation.core.utils.TypeSafeConfigUtils;
import com.hp.g11n.automation.passolo.ss.score.IChain;
import com.hp.g11n.automation.passolo.ss.score.annotation.ChainData;
import com.typesafe.config.Config;
import org.reflections.Reflections;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class SourceScoringConfigUtil {
    private static String CONFIG_DIR="D:/codes/HP/codes/ezl10n-ss/psl-generate-sourcescoring-report/psl-generate-sourcescoring-report/";
    private static String CONFIG_PATH= "/src/main/config/psl-generate-sourcescoring-report.conf";
    static Config config = TypeSafeConfigUtils.parse(Paths.get(CONFIG_DIR,CONFIG_PATH));
    private static final Reflections REFLECTIONS = ReflectUtils.buildReflections(
            "com.hp.g11n.automation.passolo.ss.score.impl"
    );
    private static List<String> checkboxNames= new ArrayList<>();
    private static List<Class> chains= new ArrayList<>();

    static{
        REFLECTIONS.getTypesAnnotatedWith(ChainData.class).stream().sorted((a,b) ->
                        Integer.compare(a.getAnnotation(ChainData.class).order(), b.getAnnotation(ChainData.class).order())
        ).forEach(c -> {
            ChainData data = c.getAnnotation(ChainData.class);
            checkboxNames.add(data.name());
            chains.add(data.chainClass());
        });
    }

    public static Config getConfig(){
        return config;
    }
    public static Object getValue(String key){
        return config.getValue(key);
    }


    public static List<String> checkBoxs(){
        return checkboxNames;
    }

    public static List<Class> chainClassList(){
        return chains;
    }
}
