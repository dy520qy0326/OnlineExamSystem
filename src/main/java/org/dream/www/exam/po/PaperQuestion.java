package org.dream.www.exam.po;

import javax.persistence.*;

/**
 * 试卷试题
 */
@Entity
@Table(name="exam_paper_question")
public class PaperQuestion {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    /**
     * 分数
     */
    @Column(precision = 6, scale = 1)
    private Double score;

    /**
     * 序号
     * 用于排序
     */
    private Integer no;

    /**
     * 试题
     * 
     */
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    /**
     * 试卷
     */
    @ManyToOne
    @JoinColumn(name = "paper_id")
    private Paper paper;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Paper getPaper() {
        return paper;
    }

	public void setScore(Double score) {
		this.score = score;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}
    
}
 