package org.dream.www.exam.po;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.dream.www.sys.po.User;
import org.springframework.context.annotation.Lazy;

/**
 * 试卷
 */
@Entity
@Table(name = "exam_paper")
public class Paper {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * 名称
     */
    private String name;

    /**
     * 总分数
     */
    @Column(precision = 6, scale = 1) // 5位整数，1位小数
    private Double score;

    /**
     * 分钟数
     */
    private Integer minutes;

    /**
     * 试卷试题集合
     */
    @OneToMany(mappedBy = "paper")
    private List<PaperQuestion> paperQuestions;

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    /**
     * 创建人
     */
    @ManyToOne
    @JoinColumn(name = "create_id")
    private User createUser;

    public Paper() {
    	
    }

    @ManyToMany(mappedBy = "papers")
    @Lazy(false)
    private List<Exam> exams;

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public List<PaperQuestion> getPaperQuestions() {
        return paperQuestions;
    }

    public void setPaperQuestions(List<PaperQuestion> paperQuestions) {
        this.paperQuestions = paperQuestions;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    
}
