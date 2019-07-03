package org.dream.www.sys.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import org.dream.www.sys.service.SysService;
import org.dream.www.sys.util.SysConstant;
import org.dream.www.sys.util.SysUtil;
import org.dream.www.sys.vo.WoMenu;
import org.dream.www.sys.vo.WoUser;


@Controller
@SessionAttributes({SysConstant.SESSION_USER,SysConstant.SESSION_LOGIN_NAME,SysConstant.SESSION_LOGIN_ERROR})

public class SysController {

	@Resource // @Autowired
	private SysService sysService;

	/**
	 * @param menu
	 * @param map
	 * @return
	 */
	@RequestMapping("/")
	String toSysIndex (String menu, Map<String, Object> map) {


		try {
			// 从session中获取用户信息
			WoUser u = SysUtil.getCurrentUser(map);
//			 设置菜单的选中状态
						if (menu != null) {
							// 遍历父菜单
							for (WoMenu m : u.getMenus()) {
								// 遍历子菜单
								for (WoMenu c : m.getChildren()) {
									if (c.getActive()) {
										if (c.getId().equals(menu)) {
											break;
										}
										c.setActive(false);
										m.setActive(false);
									} else if (c.getId().equals(menu)) {
										c.setActive(true);
										m.setActive(true);
									}
								}
							}
						}
			return "sysindex";
		} catch (Exception e) {
			return "redirect:/syslogin";
		}
	}

	/**
	 * 登录页面加载
	 * @return
	 */
	@GetMapping("/syslogin")
	String login () {
		return "syslogin";
	}



	@PostMapping("/syslogin")
	String login (String loginName, String password,String currentRole, String remberloginName,Map<String, Object>map,HttpServletResponse resp,HttpServletRequest req) {

		if ("on".equals(remberloginName)) {
			Cookie ck = new Cookie("loginName", loginName);
			ck.setMaxAge(60*60*24);
			ck.setPath("/");
			resp.addCookie(ck);
		}else {
			Cookie[] cookies = req.getCookies();
			if (cookies!=null) {
				for (Cookie cookie : cookies) {
					if(cookie.getName().equals("loginName")){
						cookie.setMaxAge(0);
						cookie.setPath("/");
						resp.addCookie(cookie);
					}
				}
			}
		}
		try {
			WoUser u = sysService.authenticate(loginName, password, currentRole);
			map.put(SysConstant.SESSION_USER, u);
			map.put(SysConstant.SESSION_LOGIN_ERROR, null);
			return "redirect:/";
		} catch (Exception e) {
			map.put(SysConstant.SESSION_LOGIN_ERROR, e);
			return "redirect:/syslogin";
		}
	}
	@GetMapping("/logout")
	String logout (Map<String, Object>map,SessionStatus status) {
		
		map.remove(SysConstant.SESSION_USER);
		status.setComplete();
		return "redirect:/";
	}
}
