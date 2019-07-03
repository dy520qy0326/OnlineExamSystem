package org.dream.www.exam.service;

import java.util.List;

import org.dream.www.common.entity.WoPage;
import org.dream.www.exam.dto.KnowledgeDto;
import org.dream.www.exam.po.Knowledge;

/**
 * Author: nitong
 * Date: Create in 2019/6/25 11:10
 * Description:
 * Modified by:
 */
public interface KnowledgeService {

    WoPage<KnowledgeDto> getPageDate(Long start, Long length, String search, String orderType);

	List<Object[]> getQuestionData();

	List<KnowledgeDto> getData();
	
	List<Knowledge> getKnowledge();

	void delete(int[] id);

	KnowledgeDto findById(Integer id);

	void update(KnowledgeDto k);

	void create(KnowledgeDto k);
}
