package org.dream.www.exam.dto;

import java.util.ArrayList;
import java.util.List;

import org.dream.www.common.util.WoUtil;
import org.dream.www.exam.po.Choice;
import org.dream.www.exam.po.Question;

public class ChoiceDto {

	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 编号
	 */
	private String no;
	
	private String questionId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public ChoiceDto() {
		super();

	}
	

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public ChoiceDto(Choice po) {
		this.id = po.getId();
		this.description = po.getDescription();
		this.no = po.getNo();
		
		if (po.getQuestion()!=null) {
			this.questionId = po.getQuestion().getId()+"";
		}
	}

	public static List<ChoiceDto> getDtos(List<Choice> ch) {
		List<ChoiceDto> dtos = new ArrayList<>();
		for (Choice po : ch) {
			dtos.add(new ChoiceDto(po));
		}
		return dtos;
	}
	
	 public Choice createPo() {

		 Choice k = new Choice();
			
	    	if (!WoUtil.isEmpty(id)) {
				k.setId(id);
			}
			
			k.setDescription(description);
			
			k.setNo(no);
			
			if (!WoUtil.isEmpty(questionId)) {
				
				Question question = new Question();
				question.setId(Integer.parseInt(questionId));
				k.setQuestion(question);
			}
			return k;

		}

}
