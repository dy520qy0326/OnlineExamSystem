package org.dream.www.sys.service.impl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import org.dream.www.common.util.WoUtil;
import org.dream.www.sys.exception.SysException;
import org.dream.www.sys.po.Permission;
import org.dream.www.sys.po.Role;
import org.dream.www.sys.po.User;
import org.dream.www.sys.repository.PermissionRepository;
import org.dream.www.sys.repository.RoleRepository;
import org.dream.www.sys.repository.UserRepository;
import org.dream.www.sys.service.SysService;
import org.dream.www.sys.util.SysConstant;
import org.dream.www.sys.vo.WoMenu;
import org.dream.www.sys.vo.WoUser;

@Service
@Transactional
public class SysServiceImpl implements SysService {
	
	@Resource
	private UserRepository UserRepository;
	
	@Resource
	private PermissionRepository permissionRepository;
	
	@Resource RoleRepository roleRepository;
	
	@Override
	public WoUser authenticate(String loginName, String password,String currentRole) {
			
			User user = UserRepository.findByloginName(loginName);
			
			if (user==null) {
				throw new SysException(SysConstant.ERR_LOGIN);
		
			}else if (!user.getPassword().equals(WoUtil.getMD5(user.getId().toString(), password))) {
				throw new SysException(SysConstant.ERR_LOGIN);
			}
			List<Role> roles = user.getRoles();
			Role role = roleRepository.findByName(currentRole);
			if (role==null&& !roles.contains(role)) {
				throw new SysException(SysConstant.ERR_LOGIN_ROLE);
			}
			WoUser woUser = new WoUser();
			woUser.setId(user.getId().toString());
			woUser.setLoginName(user.getLoginName());
			woUser.setHeadImage(user.getHeadImage());
			woUser.setCreateTime(user.getCreateTime());
			
			Boolean isAdmin = false;
			for (Role r : roles) {
				if (r.getId() == 1) {
					isAdmin = true;
				}
			}
			
			List<Permission> menus = null;
			
			if (isAdmin) {
				menus = permissionRepository.findAll();
			}else {
				menus = permissionRepository.findAllMenusByUserId(user.getId());
			}
		
			Collections.sort(menus, new Comparator<Permission> () {

				@Override
				public int compare(Permission o1, Permission o2) {
					return o1.getLevel().compareTo(o2.getLevel());
				}});
			// 3.4.组装菜单数据
			List<WoMenu> ms = new ArrayList<WoMenu>();
			for (Permission m : menus) {
				// 如果是顶级菜单，则添加到结果中
				if (m.getParent() == null) {
					ms.add(new WoMenu(m.getId().toString(), m.getName(), m.getIcon(), m.getUrl()));	
				} else {
					ms.get(ms.size()-1).getChildren().add(new WoMenu(m.getId().toString(), m.getName(), m.getIcon(), m.getUrl()));
				}
			}
			// 3.5.设置菜单默认选中
			ms.get(0).setActive(true); // 第一个父菜单
			ms.get(0).getChildren().get(0).setActive(true); // 第一个子菜单
			// 3.6.关联用户和菜单
			woUser.setMenus(ms);
		return woUser;
	}


}
