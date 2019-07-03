package org.dream.www.exam.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import org.dream.www.common.entity.WoPage;
import org.dream.www.common.util.WoUtil;
import org.dream.www.exam.dto.AnswerPaperDto;
import org.dream.www.exam.po.AnswerPaper;
import org.dream.www.exam.repository.AnswerPaperRepository;
import org.dream.www.sys.service.AnswerPaperService;

/** 
 * @author 作者 邓烨 秦烨
 * @date 创建时间：2019年6月24日 下午5:15:09 
 * @version 1.0 
 * @parameter 
 * @since 
 * @return 
 */

@Service
@Transactional
public class AnswerPaperServiceImpl  implements AnswerPaperService{
	
	@Resource
	private AnswerPaperRepository answerPaperRepository;


	@Override
	public WoPage<AnswerPaperDto> getPageData(Long start, Long length, String search, String orderType,Integer userId) {
		// 分页数据
		Pageable p = PageRequest.of(start.intValue() / length.intValue(), length.intValue(),
				"desc".equals(orderType) ? Direction.DESC : Direction.ASC, "id");
		Page<AnswerPaper> data = null;
		// 判断查询字符串是否为空
		if (WoUtil.isEmpty(search)) {
			data = answerPaperRepository.findAllByStudentId(userId,p);
		} else {
			data = answerPaperRepository.findAllByStudentIdAndPaperNameLike(userId,"%" + search + "%", p);
		}
		// 将po的分页数据，转化为dto的分页数据
		return new WoPage<AnswerPaperDto>(AnswerPaperDto.getDtos(data.getContent()), data.getTotalElements());
	}

	
	

	

}
