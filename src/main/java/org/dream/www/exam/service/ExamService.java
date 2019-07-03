package org.dream.www.exam.service;

import org.dream.www.common.entity.WoPage;
import org.dream.www.exam.dto.ExamDto;

/** * @author 作者 E-mail:
 * @date 创建时间：2019年7月1日 下午7:36:50 
 * @version 1.0 
 * @parameter 
 * @since 
 * @return 
 */
public interface ExamService {

	WoPage<ExamDto> getPageDate(Long start, Long length, String search, String orderType, String createUserId);

	void delete(int[] id);

	ExamDto findById(Integer id);

	void update(ExamDto ex);

	void create(ExamDto ex);

	void startexam(Integer id);

	void endtexam(Integer id);

}
