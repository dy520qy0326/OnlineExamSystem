package org.dream.www.sys.po;

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

/**
 * 权限
 * @author Demon
 *
 */
@Entity
@Table(name="sys_permission")
public class Permission {

	/**
	 * 权限ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/**
	 * 权限名称
	 */
	@Column(length = 20, nullable = false)
	private String name;
	
	/**
	 * 类型  1 菜单  2 按钮
	 */
	@Column(length = 1)
	private String type;
	
	/**
	 * 地址：点击菜单跳转url
	 */
	@Column(length = 200)
	private String url;
	
	/**
	 * 图标：图标资源
	 */
	@Column(length = 200)
	private String icon;
	
	/**
	 * 编号，固定长度4位，每2为为一级，假设父菜单编号：0100，则子菜单：01XX
	 */
	@Column(length = 200)
	private Integer level;
	
	/**
	 * 描述
	 */
	@Column(length = 40)
	private String description;

	/**
	 * 上级权限 多对一到权限
	 */
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Permission parent;
	
	/**
	 * 下级权限 一对多到权限 放弃维护
	 */
	@OneToMany(mappedBy = "parent")
	private List<Permission> children;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the parent
	 */
	public Permission getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Permission parent) {
		this.parent = parent;
	}

	/**
	 * @return the children
	 */
	public List<Permission> getChildren() {
		return children;
	}

	/**
	 * @param children the children to set
	 */
	public void setChildren(List<Permission> children) {
		this.children = children;
	}

	public Permission(Integer id, String name, String type, String icon, Integer level, String url) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.icon = icon;
		this.level = level;
		this.url = url;
	}

	public Permission() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
}
