package org.dream.www.sys.service;

import java.util.List;

import org.dream.www.common.entity.WoPage;
import org.dream.www.sys.dto.RoleDto;
import org.dream.www.sys.vo.WoRole;

/** * @author 作者 E-mail:
 * @date 创建时间：2019年6月21日 下午3:38:08 
 * @version 1.0 
 * @parameter 
 * @since 
 * @return 
 */

public interface RoleService {

	List<WoRole> findAllToWoRole();

	WoPage<RoleDto> getPageData(Long start, Long length, String search, String orderType) ;

	void create(RoleDto r);

	void delete(int[] id);
	void delete(int id);

	RoleDto findById(Integer id);

	void update(RoleDto r);

}
