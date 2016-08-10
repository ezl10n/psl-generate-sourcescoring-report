package com.hp.g11n.automation.passolo.ss.util;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.reflections.Reflections;

import com.hp.g11n.automation.core.utils.ReflectUtils;
import com.hp.g11n.automation.core.utils.TypeSafeConfigUtils;
import com.hp.g11n.automation.passolo.ss.score.annotation.RuleData;
import com.typesafe.config.Config;

public class SourceScoringConfigUtil {
	private static String CONFIG_DIR = "D:/HPGit/psl-generate-sourcescoring-report/";
	private static String CONFIG_PATH = "/src/main/config/psl-generate-sourcescoring-report.conf";
	static Config config = TypeSafeConfigUtils.parse(Paths.get(CONFIG_DIR,
			CONFIG_PATH));
	private static final Reflections REFLECTIONS = ReflectUtils
			.buildReflections("com.hp.g11n.automation.passolo.ss.score.impl");
	private static List<String> checkboxNames = new ArrayList<>();
	private static List<Class> rules = new ArrayList<>();

	static {
		REFLECTIONS
				.getTypesAnnotatedWith(RuleData.class)
				.stream()
				.sorted((a, b) -> Integer.compare(
						a.getAnnotation(RuleData.class).order(), b
								.getAnnotation(RuleData.class).order()))
				.forEach(c -> {
					RuleData data = c.getAnnotation(RuleData.class);
					checkboxNames.add(data.name());
					rules.add(data.ruleClass());
				});
	}

	public static Config getConfig() {
		return config;
	}

	public static Object getValue(String key) {
		return config.getValue(key);
	}

	public static List<String> checkBoxs() {
		return checkboxNames;
	}

	public static List<Class> ruleClassList() {
		return rules;
	}
}
