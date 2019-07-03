package org.dream.www.exam.po;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.dream.www.sys.po.User;
import org.springframework.context.annotation.Lazy;

/**
 * 考试
 */
@Entity
@Table(name = "exam_exam")
public class Exam {

	/**
	 * 主键，自增长
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 开始时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;

	/**
	 * 结束时间：如果该属性有值，表示考试结束
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 试卷：多对多
	 */
	@ManyToMany(targetEntity = Paper.class ,fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinTable(name = "exam_related_paper",joinColumns = @JoinColumn(name = "exam_id"), inverseJoinColumns = @JoinColumn(name = "paper_id"))
	private List<Paper> papers;

	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	/**
	 * 考试与创建人用户：多对一
	 */
	@ManyToOne
	@JoinColumn(name = "create_id")
	@JsonIgnoreProperties(value = "roles")
	private User createUser;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<Paper> getPapers() {
		return papers;
	}

	public void setPapers(List<Paper> papers) {
		this.papers = papers;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Exam() {
		super();
	}

}
