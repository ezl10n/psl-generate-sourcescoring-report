package com.hp.g11n.automation.passolo.ss.util;

import com.hp.g11n.sdl.psl.interop.core.IPassoloApp;
import com.hp.g11n.sdl.psl.interop.core.IPslProject;
import com.hp.g11n.sdl.psl.interop.core.IPslSourceLists;
import com.hp.g11n.sdl.psl.interop.core.impl.impl.PassoloApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil{
	private final Logger log = LoggerFactory.getLogger(getClass());

	public static IPslSourceLists getSourceLists(String resoucePath) {
		IPassoloApp app = PassoloApp.getInstance();
		IPslProject project = app.open(resoucePath);
		return project.getSourceLists();
	}

}
