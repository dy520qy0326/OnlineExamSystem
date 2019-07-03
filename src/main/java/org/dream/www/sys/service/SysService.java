package org.dream.www.sys.service;

import org.dream.www.sys.vo.WoUser;

/** * @author 作者 E-mail:
 * @date 创建时间：2019年6月20日 上午10:29:50 
 * @version 1.0 
 * @parameter 
 * @since 
 * @return 
 */
public interface SysService {
	

	WoUser authenticate(String loginName, String passWord, String currentRole);
}
