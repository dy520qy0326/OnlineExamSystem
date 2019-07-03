package org.dream.www.exam.service.impl;

import java.io.File;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dream.www.exam.repository.ChoiceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.dream.www.common.entity.WoPage;
import org.dream.www.common.util.WoUtil;
import org.dream.www.exam.dto.QuestionDto;
import org.dream.www.exam.exception.ExamException;
import org.dream.www.exam.po.Question;
import org.dream.www.exam.repository.QuestionRepository;
import org.dream.www.exam.service.QuestionService;
import org.dream.www.exam.util.ExamConstant;

/** * @author 作者 E-mail:
 * @date 创建时间：2019年6月29日 上午10:31:10 
 * @version 1.0 
 * @parameter 
 * @since 
 * @return 
 */

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

	private final static Logger LOG = LogManager.getLogger(QuestionServiceImpl.class);

	@Resource
	private QuestionRepository questionRepository;
	@Resource
	private ChoiceRepository choiceRepository;
	@Override
	public WoPage<QuestionDto> getPageDate(Long start, Long length, String search, String orderType) {
		// 分页数据
		Pageable p = PageRequest.of(start.intValue()/ length.intValue(), length.intValue());
		Page<Question> data=null;
		// 判断查询字符串是否为空
		if (WoUtil.isEmpty(search)) {
			data=questionRepository.findAll(p);
		} else {
			data =  questionRepository.findAllByNameLike("%" + search + "%", p);
		}
		// 将po的分页数据，转化为dto的分页数据
		return new WoPage<QuestionDto>(QuestionDto.getDtos(data.getContent()), data.getTotalElements());



	}


	private void saveFile (QuestionDto q, MultipartFile imageFile, String dir) {
		try {
			if (!(imageFile == null || "".equals(imageFile.getOriginalFilename()))) {
				// 保存试题文件，到tomcat下项目路径
				// 保存之后，可以通过http://localhost:8080/qf-exam/question/xxx.jpg访问
				imageFile.transferTo(new File (dir + "question/" + imageFile.getOriginalFilename()));
				q.setImage("question/" + imageFile.getOriginalFilename());
			}
		} catch (Exception e) {
			LOG.error("保存试题图片出错", e);
			throw new ExamException (e, ExamConstant.ERR_QUESTION_IMG);
		}
	}

	@Override
	public QuestionDto findById(Integer id) {

		return new QuestionDto(questionRepository.findById(id).get());
	}

	@Override
	public void delete(int[] id) {
		if (id!=null) {
			for (int i : id) {

				this.delete(i);
			}
		}

	}

	@Override
	public void delete(int id) {
		choiceRepository.deleteAllByQuestionId(id);
		questionRepository.deleteById(id);
	}

	@Override
	public void update(QuestionDto q, MultipartFile imageFile, String dir) {
		this.saveFile(q, imageFile, dir);
		Question question = questionRepository.findById(q.getId()).get();
		q.updatePO(question);
		questionRepository.save(question);

	}



	@Override
	public Integer create(QuestionDto q, MultipartFile imageFile, String dir) {
		this.saveFile(q, imageFile, dir);
		// 创建PO并保存
		Question po = q.createPO();
		questionRepository.save(po);
		return po.getId();

	}

}
