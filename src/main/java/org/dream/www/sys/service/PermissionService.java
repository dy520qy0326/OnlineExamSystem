package org.dream.www.sys.service;

import org.dream.www.common.entity.WoPage;
import org.dream.www.sys.dto.PermissionDto;


/** * @author 作者 E-mail:
 * @date 创建时间：2019年6月25日 下午8:36:49 
 * @version 1.0 
 * @parameter 
 * @since 
 * @return 
 */
public interface PermissionService {

	WoPage<PermissionDto> getPageData(Long start, Long length, String search, String orderType);

	void create(PermissionDto p);

	PermissionDto findById(Integer id);

	void update(PermissionDto r);

	void delete(int[] id);

}
