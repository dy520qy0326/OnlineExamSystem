package org.dream.www.school.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.dream.www.sys.po.User;

/**
 * 	学生
 * @author Administrator
 *
 */
@Entity
@Table(name = "sch_student")
public class Student {
	/**
	 *	id
	 *	自增，主键
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * 	姓名
	 */
	@Column(length = 20)
	private String name;
	
	/**
	 *	性别
	 */
	@Column(length = 1)
	private String gender;
	
	/**
	 *	头像 
	 */
	private String headImage;
	
	/**
	 *	用户
	 */
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	/**
	 *	创建人 
	 *	多对一到用户
	 */
	@ManyToOne
	@JoinColumn(name = "create_id")
	private User createUser;
	
	/**
	 *	创建时间 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date creatTime;

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public Student() {
		super();
	}
	
}
