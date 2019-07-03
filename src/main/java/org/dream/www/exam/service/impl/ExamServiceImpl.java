package org.dream.www.exam.service.impl;

import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.dream.www.common.entity.WoPage;
import org.dream.www.common.util.WoUtil;
import org.dream.www.exam.dto.ExamDto;
import org.dream.www.exam.dto.KnowledgeDto;
import org.dream.www.exam.po.Exam;
import org.dream.www.exam.po.Knowledge;
import org.dream.www.exam.repository.ExamRepository;
import org.dream.www.exam.service.ExamService;
import org.dream.www.sys.exception.SysException;
import org.dream.www.sys.util.SysConstant;

/** * @author 作者 E-mail:
 * @date 创建时间：2019年7月1日 下午7:39:02 
 * @version 1.0 
 * @parameter 
 * @since 
 * @return 
 */
@Service
@Transactional
public class ExamServiceImpl implements ExamService {

	@Resource
	private ExamRepository examRepository;

	@Override
	public WoPage<ExamDto> getPageDate(Long start, Long length, String search, String orderType,String createUserId) {
		// 分页数据
		Pageable p = PageRequest.of(start.intValue()/ length.intValue(), length.intValue());
		Page<Exam> data=null;
		Integer id = Integer.parseInt(createUserId);
		// 判断查询字符串是否为空
		if (WoUtil.isEmpty(search)) {
			data=examRepository.findAllByCreateUserId(id,p);
		} else {
			data =  examRepository.findAllByCreateUserIdAndNameLike(id,"%" + search + "%", p);
		}
		// 将po的分页数据，转化为dto的分页数据
		return new WoPage<ExamDto>(ExamDto.getDtos(data.getContent()), data.getTotalElements());

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

	public void delete(int id) {
		try {
			examRepository.deleteById(id);
		} catch (Exception e) {
			throw new SysException(SysConstant.ERR_DELETE_KNOWLEDGE);
		}

	}

	@Override
	public ExamDto findById(Integer id) {
		Exam exam = examRepository.findById(id).get();


		return new ExamDto(exam); 
	}

	@Override
	public void update(ExamDto ex) {
		Exam po = ex.createPo();
		try {
			examRepository.save(po);
		} catch (Exception e) {
			throw new SysException(SysConstant.ERR_CREATE_KNOWLEDGE);
		}

	}

	@Override
	public void create(ExamDto ex) {
		Exam po = ex.createPo();
		try {
			examRepository.save(po);
		} catch (Exception e) {
			throw new SysException(SysConstant.ERR_CREATE_KNOWLEDGE);
		}
	}

	@Override
	public void startexam(Integer id) {
		Exam po = examRepository.findById(id).get();

		po.setStartTime(new Date());

		examRepository.save(po);
	}

	@Override
	public void endtexam(Integer id) {

		Exam po = examRepository.findById(id).get();

		po.setEndTime(new Date());

		examRepository.save(po);
	}

}
