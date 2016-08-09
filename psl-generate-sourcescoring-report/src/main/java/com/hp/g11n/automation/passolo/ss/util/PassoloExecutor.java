package com.hp.g11n.automation.passolo.ss.util;

import com.hp.g11n.sdl.psl.interop.core.IPslProject;
import com.hp.g11n.sdl.psl.interop.core.IPslSourceLists;

@FunctionalInterface
public interface PassoloExecutor {
     void execute(IPslProject project,IPslSourceLists sourceLists);
}