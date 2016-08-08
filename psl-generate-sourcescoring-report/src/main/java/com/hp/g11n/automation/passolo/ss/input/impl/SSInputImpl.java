package com.hp.g11n.automation.passolo.ss.input.impl;

import com.hp.g11n.automation.passolo.ss.input.ISSInput;
import com.hp.g11n.sdl.psl.interop.core.IPassoloApp;
import com.hp.g11n.sdl.psl.interop.core.IPslProject;
import com.hp.g11n.sdl.psl.interop.core.IPslSourceLists;
import com.hp.g11n.sdl.psl.interop.core.impl.impl.PassoloApp;

/**
 * 
 * @Descripation 读取 .lpu文件实现类，根据lpu文件地址读取并解析文件中的对象
 * @CreatedBy: Ali Cao
 * @Date: 2016年8月5日
 * @Time: 上午9:41:13
 *
 */
public class SSInputImpl implements ISSInput {

	@Override
	public IPslSourceLists getSourceLists(String path) {
		IPassoloApp app = PassoloApp.getInstance();
		IPslProject project = app.open(path);
		return project.getSourceLists();
	}

}
