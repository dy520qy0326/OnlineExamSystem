package org.dream.www.exam.po;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.dream.www.sys.po.User;

/**
 * 知识点
 * @author Administrator
 *
 */
@Entity
@Table(name = "exam_knowledge")
public class Knowledge {
	/**
	 * id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 类型
	 */
	private String type;
	
	/**
	 * 编号
	 */
	private String no;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 上级知识点（多对一到知识点）
	 */
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Knowledge parent;
	
	/**
	 * 创建人
	 * 知识点多对一到用户
	 * 
	 */
	@ManyToOne
	@JoinColumn(name = "create_id")
	private User createUser;
	
	/**
	 * 创建时间
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Knowledge getParent() {
		return parent;
	}
	public void setParent(Knowledge parent) {
		this.parent = parent;
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
