package org.dream.www.sys.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 数据字典
 *
 */
@Entity
@Table(name = "sys_dictionary")
public class Dictionary {

	/**
	 * 主键，自增
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * 名称
	 */
	@Column(length = 30)
	private String name;

	/**
	 * 值
	 */
	@Column(length = 50)
	private String value;

	/**
	 * 类型
	 */
	@Column(length = 30)
	private String type;

	/**
	 * 描述
	 */
	@Column(length = 200)
	private String description;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
