package org.dream.www.sys.util;

import java.util.Map;

import org.dream.www.common.exception.WoException;
import org.dream.www.sys.vo.WoUser;

public class SysUtil {
	
	/**
	 * 获取当前用户信息
	 * @param map controller方法中传入的参数
	 * @return
	 */
	public static WoUser getCurrentUser (Map<String, Object>map) {
		WoUser u = (WoUser)map.get(SysConstant.SESSION_USER);
		if (u == null) {
			throw new WoException (SysConstant.ERR_LOGIN);
		}
		return u;
	}
}
