package org.dream.www.exam.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.dream.www.exam.po.Choice;
import org.dream.www.exam.repository.ChoiceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.dream.www.common.entity.WoPage;
import org.dream.www.common.util.WoUtil;
import org.dream.www.exam.dto.KnowledgeDto;
import org.dream.www.exam.po.Knowledge;
import org.dream.www.exam.repository.KnowledgeRepository;
import org.dream.www.exam.service.KnowledgeService;
import org.dream.www.sys.exception.SysException;
import org.dream.www.sys.util.SysConstant;
import sun.security.krb5.internal.tools.Klist;

/**
 * Author: nitong
 * Date: Create in 2019/6/25 11:10
 * Description:
 * Modified by:
 */
@Service
@Transactional
public class KnowledgeServiceImpl implements KnowledgeService {

	@Resource
	private KnowledgeRepository knowledgeRepository;


	@Override
	public WoPage<KnowledgeDto> getPageDate(Long start, Long length, String search, String orderType) {
		// 分页数据
		Pageable p = PageRequest.of(start.intValue()/ length.intValue(), length.intValue());
		Page<Knowledge> data=null;
		// 判断查询字符串是否为空
		if (WoUtil.isEmpty(search)) {
			data=knowledgeRepository.findAll(p);
		} else {
			data =  knowledgeRepository.findAllByNameLike("%" + search + "%", p);
		}
		// 将po的分页数据，转化为dto的分页数据
		return new WoPage<KnowledgeDto>(KnowledgeDto.getDtos(data.getContent()), data.getTotalElements());

	}


	@Override
	public List<Object[]> getQuestionData() {
		return knowledgeRepository.findQuestionData();
	}

	@Override
	public List<KnowledgeDto> getData() {
		return KnowledgeDto.getDtos(this.knowledgeRepository.findAll());
	}


	@Override
	public List<Knowledge> getKnowledge() {
		return knowledgeRepository.findAll();
	}

	@Override
	public void delete(int[] id) {

		if (id==null) {
			throw new SysException(SysConstant.ERR_DELETE_KNOWLEDGE);
		}
		for (int i : id) {
			this.delete(i);
		}
	}


	public void delete(int i) {

		try {
			knowledgeRepository.deleteById(i);
		} catch (Exception e) {
			throw new SysException(SysConstant.ERR_DELETE_KNOWLEDGE);
		}
	}


	@Override
	public KnowledgeDto findById(Integer id) {


		Knowledge knowledge = knowledgeRepository.findById(id).get();


		return new KnowledgeDto(knowledge); 
	}


	@Override
	public void update(KnowledgeDto k) {
		Knowledge po = k.createPo();
		try {
			knowledgeRepository.save(po);
		} catch (Exception e) {
			throw new SysException(SysConstant.ERR_UPDATE_KNOWLEDGE);
		}
	}


	@Override
	public void create(KnowledgeDto k) {
		Knowledge po = k.createPo();
		try {
			knowledgeRepository.save(po);
		} catch (Exception e) {
			throw new SysException(SysConstant.ERR_CREATE_KNOWLEDGE);
		}
		
	}

	@Override
	public List<KnowledgeDto> findAll() {

		List<Knowledge>kList = knowledgeRepository.findAll();

		return  KnowledgeDto.getDtos(kList);

	}


}