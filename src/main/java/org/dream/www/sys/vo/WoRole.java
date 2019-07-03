package org.dream.www.sys.vo;

import java.io.Serializable;

/** * @author 作者 E-mail:
 * @date 创建时间：2019年6月23日 下午4:08:00 
 * @version 1.0 
 * @parameter 
 * @since 
 * @return 
 */
public class WoRole  implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8451716159709467420L;

	/**
	 * id
	 */

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
	 * 权限集合id，多个以逗号隔开
	 */
	private String permissionsId;
	

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


	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public WoRole() {
		// TODO Auto-generated constructor stub
	}

}
