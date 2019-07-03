package org.dream.www.exam.po;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
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
 *  答卷
 *
 */
@Entity
@Table(name = "exam_answer_paper")
public class AnswerPaper {
    
	/**
     * ID
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    
    /**
     * 学生
     */
    @ManyToOne
    @JoinColumn(name = "student_id")
    private User student;
    
    /**
     * 试卷
     */
    @ManyToOne
    @JoinColumn(name = "paper_id")
    private Paper paper;
    
    /**
     * 答题项集合
     */
    @OneToMany(mappedBy = "paper")
    private List<AnswerItem> items;
    
    /**
     * 分数
     */
    @Column(precision = 6, scale = 1)
    private Double score;
    
    /**
     * 考试
     */
    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;
    
    /**
     * 考试开始时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    
    /**
     * 考试结束时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    public AnswerPaper() {
    }

    public AnswerPaper(Integer id, User student, Paper paper, List<AnswerItem> items, Double score, Exam exam, Date startTime, Date endTime) {
        this.id = id;
        this.student = student;
        this.paper = paper;
        this.items = items;
        this.score = score;
        this.exam = exam;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public List<AnswerItem> getItems() {
        return items;
    }

    public void setItems(List<AnswerItem> items) {
        this.items = items;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
