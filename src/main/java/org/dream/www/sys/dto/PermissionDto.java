package org.dream.www.sys.dto;

import java.util.ArrayList;
import java.util.List;

import org.dream.www.common.util.WoUtil;
import org.dream.www.sys.po.Permission;

/** * @author 作者 E-mail:
 * @date 创建时间：2019年6月25日 下午8:39:50 
 * @version 1.0 
 * @parameter 
 * @since 
 * @return 
 */
public class PermissionDto {

	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 权限名
	 */
	private String name;

	private Integer level;

	private String url;

	private String parentId;
	private String parentName;

	private	String	description;

	private String childrensId;
	private String icon;
	private String type;
	
	/**
	 * @return the parentName
	 */
	public String getParentName() {
		return parentName;
	}

	/**
	 * @param parentName the parentName to set
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
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
	 * @return the parenId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parenId the parenId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
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
	 * @return the childrensId
	 */
	public String getChildrensId() {
		return childrensId;
	}

	/**
	 * @param childrensId the childrensId to set
	 */
	public void setChildrensId(String childrensId) {
		this.childrensId = childrensId;
	}
	public Permission createPo() {

		Permission p  = new Permission();
		
		p.setId(id);
		p.setName(name);
		p.setLevel(level);
		p.setDescription(description);
		p.setIcon(icon);
		p.setType(type);
		p.setUrl(url);
		Permission p2 = new Permission();
		if (!WoUtil.isEmpty(parentId)) {
			p2.setId(Integer.parseInt(parentId.toString()));
			p.setParent(p2);
		}else {
			p.setParent(null);
		}
		
		if (!WoUtil.isEmpty(childrensId)) {
			List<Permission> children= new ArrayList<Permission>();
			for (String pid : this.childrensId.split(",")) {
				Permission p3 = new Permission();
				p3.setId(Integer.parseInt(pid));
				children.add(p3);
			}
			p.setChildren(children);
		}
		return p;

	}
	public PermissionDto(Permission po) {
		super();
		this.id = po.getId();
		this.name = po.getName();
		this.level = po.getLevel();
		this.url = po.getUrl();
		this.icon=po.getIcon();
		this.type= po.getType();
		this.description = po.getDescription();
		if ( po.getParent()==null) {
			this.parentId=null;
			this.parentName=null;
		}else {
			this.parentId = po.getParent().getId().toString();
			this.parentName = po.getParent().getName();
		}
		List<Permission> children= po.getChildren();
		if (children.isEmpty()) {
			this.childrensId=null;
		}else {
			this.childrensId="";

			for (int i = 0; i < children.size(); i++) {
				if (childrensId=="") {
					childrensId+=children.get(i).getId().toString();
				}else {
					childrensId+=(","+children.get(i).getId());
				}
			}
		}
	}

	public PermissionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static List<PermissionDto> getDtos(List<Permission> content) {
		List<PermissionDto> list = new ArrayList<PermissionDto>();

		for (Permission p : content) {
			list.add(new PermissionDto(p));
		}
		return list;
	}


}
