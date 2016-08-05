package com.hp.g11n.automation.passolo.ss.score.impl;

import com.hp.g11n.automation.passolo.ss.score.IChain;

/**
 * 
 * @Descripation 根据传入的源数据和匹配目标检查
 * @CreatedBy: Ali Cao
 * @Date: 2016年8月5日
 * @Time: 上午10:19:16
 *
 */
public class ConcatenationCheckChain implements IChain{

	@Override
	public boolean check(String source, String target) {
		boolean result = false;
		if (source.startsWith(target) 
				|| source.endsWith(target)
				|| (source.toLowerCase().equals(target.toLowerCase()) 
						&& (source.substring(0,1).hashCode() -32 == source.substring(0,1).hashCode()))
				|| source.contains(target)) {
			result = true;
		}
		return result;
	}

}
