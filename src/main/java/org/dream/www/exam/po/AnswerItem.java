package org.dream.www.exam.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 答题项
 * @author:李恩俊
 * @Creation:2019年6月18日 下午5:30:07
 */
@Entity
@Table(name = "exam_answer_item")
public class AnswerItem {

	/**
	 * @author:ZHAOYANG
	 * @Creation:2019年6月18日 下午7:40:22
	 * @Explain:主键 主键自增长
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * @author:ZHAOYANG
	 * @Creation:2019年6月18日 下午7:40:22
	 * @Explain: // 答案
	 */
	@Column
	private String answer;

	/**
	 * @author:ZHAOYANG
	 * @Creation:2019年6月18日 下午7:40:22
	 * @Explain: // 分数
	 */
	@Column(precision = 6, scale = 1)
	private Double score;

	/**
	 * @author:ZHAOYANG
	 * @Creation:2019年6月18日 下午7:40:22
	 * @Explain: // 试题 多对一试题 // 外键 必须维护关系数据
	 */
	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;

	/**
	 * @author:ZHAOYANG
	 * @Creation:2019年6月18日 下午7:40:22
	 * @Explain: // 答卷 多对一到答卷 // 外键 必须维护关系数据
	 */
	@ManyToOne
	@JoinColumn(name = "paper_id")
	private AnswerPaper paper;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public AnswerPaper getPaper() {
		return paper;
	}

	public void setPaper(AnswerPaper paper) {
		this.paper = paper;
	}

	

}