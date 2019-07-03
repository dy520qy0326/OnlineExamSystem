package org.dream.www.exam.po;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.dream.www.sys.po.User;

/**
 * 试题 
 */
@Entity
@Table(name = "exam_question")
public class Question {

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/**
	 * 题干
	 */
	private String name;

	/**
	 * 选项集合 一对多到选项
	 */
	@OneToMany(mappedBy = "question")
	private List<Choice> choices;

	/**
	 * 答案
	 */
	private String answer;
	
	/**
	 * 知识点 多对一到知识点 knowledge_id
	 */
	@ManyToOne
	@JoinColumn(name = "knowledge_id")
	private Knowledge knowledge;
	
	/**
	 * 图片
	 */
	private String image;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 创建人 多对一到用户 create_id
	 */
	@ManyToOne
	@JoinColumn(name = "create_id")
	private User createUser;
	
	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	public Question() {
	}

	public Question(int id, String name, List<Choice> choices, String answer, Knowledge knowledge, String image,
			String description, User createUser, Date createTime) {
		super();
		this.id = id;
		this.name = name;
		this.choices = choices;
		this.answer = answer;
		this.knowledge = knowledge;
		this.image = image;
		this.description = description;
		this.createUser = createUser;
		this.createTime = createTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Knowledge getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(Knowledge knowledge) {
		this.knowledge = knowledge;
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

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	

}
