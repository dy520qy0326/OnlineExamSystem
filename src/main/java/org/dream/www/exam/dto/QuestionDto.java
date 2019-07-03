package org.dream.www.exam.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.dream.www.common.util.WoUtil;
import org.dream.www.exam.po.Knowledge;
import org.dream.www.exam.po.Question;
import org.dream.www.sys.po.User;

public class QuestionDto {

	private Integer id;
	private String name;
	private String answer;
	private String image;
	private String description;

	private Date createTime = new Date();

	private String createUser = "";

	private String ChoiceId = "";
	private String ChoiceNo = "";

	private Integer knowledgeId;
	private String knowledgeName = "";

	private Integer createUserId;
	private String createUserName = "";

	public Integer getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(Integer knowledgeId) {
		this.knowledgeId = knowledgeId;
	}

	public String getKnowledgeName() {
		return knowledgeName;
	}

	public void setKnowledgeName(String knowledgeName) {
		this.knowledgeName = knowledgeName;
	}

	public Integer getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getChoiceId() {
		return ChoiceId;
	}

	public void setChoiceId(String choiceId) {
		ChoiceId = choiceId;
	}

	public String getChoiceNo() {
		return ChoiceNo;
	}

	public void setChoiceNo(String choiceNo) {
		ChoiceNo = choiceNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", locale = "zh", timezone = "GMT+8") // 设置时间格式
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public QuestionDto(Question po) {
		this.id = po.getId();
		this.name = po.getName();
		this.answer = po.getAnswer();
		this.image = po.getImage();
		this.description = po.getDescription();
		this.createTime = po.getCreateTime();
		if (po.getCreateUser() != null) {
			this.createUserId = po.getCreateUser().getId();
			this.createUserName = po.getCreateUser().getLoginName();
		}
		if (po.getKnowledge() != null) {
			this.knowledgeId = po.getKnowledge().getId();
			this.knowledgeName = po.getKnowledge().getName();
		}
	}

	public QuestionDto() {

	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/**
	 * @param pos
	 * @return
	 */
	public static List<QuestionDto> getDtos(List<Question> pos) {
		List<QuestionDto> dtos = new ArrayList<QuestionDto>();
		for (Question po : pos) {
			dtos.add(new QuestionDto(po));
		}
		return dtos;
	}

	public Question createPO() {
		Question q = new Question();
		q.setName(name);
		q.setAnswer(answer);
		q.setImage(image);
		q.setDescription(description);
		if (this.knowledgeId != null) {
			Knowledge knowledge = new Knowledge();
			knowledge.setId(knowledgeId);
			q.setKnowledge(knowledge );
		}
		q.setCreateTime(new Date());
		if (this.createUserId != null) {
			User createUser = new User ();
			createUser.setId(this.createUserId);
			q.setCreateUser(createUser);
		}
		return q;
	}

	public void updatePO(Question po) {
		po.setName(name);
		po.setAnswer(answer);
		// 设置图片url
		if (!WoUtil.isEmpty(image)) {
			po.setImage(image);
		}
		po.setDescription(description);
		if (this.knowledgeId != null) {
			Knowledge knowledge = new Knowledge();
			knowledge.setId(knowledgeId);
			po.setKnowledge(knowledge );
		}
	}

	
}
