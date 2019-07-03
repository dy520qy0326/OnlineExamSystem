package org.dream.www.exam.service;


import org.dream.www.common.entity.WoPage;
import org.dream.www.exam.dto.AnswerPaperDto;




/** 
 * @author 作者 邓烨 秦烨
 * @date 创建时间：2019年6月24日 下午5:15:09 
 * @version 1.0 
 * @parameter 
 * @since 
 * @return 
 */
public interface AnswerPaperService {

	WoPage<AnswerPaperDto> getPageData(Long start, Long length, String search, String orderType, Integer userId);







	

}
