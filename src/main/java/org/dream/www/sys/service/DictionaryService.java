package org.dream.www.sys.service;

import org.dream.www.common.entity.WoPage;
import org.dream.www.sys.dto.DictionaryDto;

/** * @author 作者 E-mail:
 * @date 创建时间：2019年6月27日 下午4:53:33 
 * @version 1.0 
 * @parameter 
 * @since 
 * @return 
 */
public interface DictionaryService {

	WoPage<DictionaryDto> getPageData(Long start, Long length, String search, String orderType);

	void create(DictionaryDto d);

	DictionaryDto findById(Integer id);

	void update(DictionaryDto d);

	void delete(int[] id);

	void delete(int i);

}
