package org.dream.www.exam.service;

import org.springframework.web.multipart.MultipartFile;

import org.dream.www.common.entity.WoPage;
import org.dream.www.exam.dto.QuestionDto;

/** * @author 作者 E-mail:
 * @date 创建时间：2019年6月29日 上午10:30:41 
 * @version 1.0 
 * @parameter 
 * @since 
 * @return 
 */
public interface QuestionService {

	WoPage<QuestionDto> getPageDate(Long start, Long length, String search, String orderType);

	QuestionDto findById(Integer id);

	void delete(int[] id);

	Integer create(QuestionDto k, MultipartFile imageFile, String string);

	void delete(int id);

	void update(QuestionDto q, MultipartFile imageFile, String dir);

}
