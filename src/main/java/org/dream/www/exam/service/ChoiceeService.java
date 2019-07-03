package org.dream.www.exam.service;

import org.dream.www.common.entity.WoPage;
import org.dream.www.common.exception.WoResultCode;
import org.dream.www.exam.dto.ChoiceDto;

/** * @author 作者 E-mail:
 * @date 创建时间：2019年6月29日 下午4:00:39 
 * @version 1.0
 * @parameter
 * @since
 * @return
 */
public interface ChoiceeService {

	WoPage<ChoiceDto> getPageDate(Long start, Long length, String search, String orderType,Integer questionId);

	WoResultCode delete(int[] id);

	ChoiceDto findById(Integer id);

	void update(ChoiceDto k);

	void create(ChoiceDto k);

	WoPage<ChoiceDto> getPageDate1(Long start, Long length, String search, String orderType);

	void upChoice(Integer id);

	void downChoice(Integer id);

}
