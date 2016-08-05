package com.hp.g11n.automation.passolo.ss.input;

import com.hp.g11n.sdl.psl.interop.core.IPslSourceLists;

public interface ISSInput {
	/**
	 * 
	 * @Descripation 读取 .lpu文件接口，根据lpu文件地址读取并解析文件中的对象
	 * @CreatedBy: Ali Cao
	 * @Date: 2016年8月5日
	 * @Time: 上午9:41:13
	 *
	 */
	public IPslSourceLists getSourceLists(String path);

}
