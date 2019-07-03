package org.dream.www.sys.dto;

import java.util.ArrayList;
import java.util.List;

import org.dream.www.common.util.WoUtil;
import org.dream.www.sys.po.Permission;
import org.dream.www.sys.po.Role;

/** * @author 作者 E-mail:
 * @date 创建时间：2019年6月23日 下午4:16:27 
 * @version 1.0 
 * @parameter 
 * @since 
 * @return 
 */
public class RoleDto {

	private Integer id;

	/**
	 * 名称
	 */
	
	private String name;

	/**
	 * 描述
	 */
	
	private String description;

	/**
	 * id，多个以逗号隔开
	 */
	private String permissionsId;
	
	private String permissionsName;
	/**
	 * @return the permissionsName
	 */
	public String getPermissionsName() {
		return permissionsName;
	}

	/**
	 * @param permissionsName the permissionsName to set
	 */
	public void setPermissionsName(String permissionsName) {
		this.permissionsName = permissionsName;
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
	 * @return the permissionsId
	 */
	public String getPermissionsId() {
		return permissionsId;
	}

	/**
	 * @param permissionsId the permissionsId to set
	 */
	public void setPermissionsId(String permissionsId) {
		this.permissionsId = permissionsId;
	}

	public RoleDto() {
		// TODO Auto-generated constructor stub
	}
	
	public RoleDto(Role po) {
		this.id = po.getId();
		this.name = po.getName();
		this.description = po.getDescription();
	}

	public Role createPO () {
		Role r = new Role ();
		 r.setId(this.id);
		r.setName(name);
		r.setDescription(description);
		
		// 设置用户和角色的关系数据
		if (!WoUtil.isEmpty(permissionsId)) {
			List<Permission> pList = new ArrayList<Permission> ();
			for (String pId : this.permissionsId.split(",")) {
				Permission p = new Permission();
				p.setId(Integer.parseInt(pId));
				pList.add(p);
			}
			r.setPermissions(pList);
		}
		return r;
	}
	

	/**
	 * @param pos
	 * @return
	 */
	public static List<RoleDto> getDtos (List<Role> pos) {
		List<RoleDto> dtos = new ArrayList<RoleDto>();
		for (Role po : pos) {
			dtos.add(new RoleDto(po));
		}
		return dtos;
	}
}
