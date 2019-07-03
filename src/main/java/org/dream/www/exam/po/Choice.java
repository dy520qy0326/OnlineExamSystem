package org.dream.www.exam.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 选项
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "exam_choice")
public class Choice implements Comparable<Choice>{

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 编号
	 */
	private String no;

	/**
	 * 试题,多对一到试题
	 */
	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;

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

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	
	@Override
	public int compareTo(Choice o) {
		return this.no.compareTo(o.no);
	}

}
