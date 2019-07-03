package org.dream.www.sys.dto;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.dream.www.common.util.WoUtil;
import org.dream.www.sys.po.Role;
import org.dream.www.sys.po.User;

public class UserDto {

	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 用户名
	 */
	private String loginName;

	/**
	 * 登录密码
	 */
	private String password;

	/**
	 * 创建时间：注解@Temporal会将当前的Date匹配到数据库中的Date类型
	 */
	private Date createTime = new Date();

	/**
	 * 角色id，多个以逗号隔开
	 */
	private String rolesId = "";
	
	/**
	 * 角色name，多个以逗号隔开
	 */
	private String rolesName = "";
	
	/**
	 * 描述
	 */
	private String description;

	public UserDto() {
	}

	public UserDto(User po) {
		this.id = po.getId();
		this.loginName = po.getLoginName();
		this.description = po.getDescription();
		this.createTime = po.getCreateTime();
		this.password = po.getPassword();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRolesId() {
		return rolesId;
	}

	public void setRolesId(String rolesId) {
		this.rolesId = rolesId;
	}

	public String getRolesName() {
		return rolesName;
	}

	public void setRolesName(String rolesName) {
		this.rolesName = rolesName;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", locale = "zh", timezone = "GMT+8") // 设置时间格式
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 创建PO
	 * @return
	 */
	public User createPO () {
		User u = new User ();
		u.setId(id);
		u.setLoginName(loginName);
		u.setPassword(password);
		u.setCreateTime(new Date());
		u.setDescription(description);
		// 设置用户和角色的关系数据
		if (!WoUtil.isEmpty(rolesId)) {
			List<Role> roles = new ArrayList<Role> ();
			for (String sId : this.rolesId.split(",")) {
				Role r = new Role ();
				r.setId(Integer.parseInt(sId));
				roles.add(r);
			}
			u.setRoles(roles);
		}
		return u;
	}
	
	/**
	 * @param pos
	 * @return
	 */
	public static List<UserDto> getDtos (List<User> pos) {
		List<UserDto> dtos = new ArrayList<UserDto>();
		for (User po : pos) {
			dtos.add(new UserDto(po));
		}
		return dtos;
	}
}
