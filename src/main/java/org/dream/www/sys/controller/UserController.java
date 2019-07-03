package org.dream.www.sys.controller;


import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.dream.www.common.entity.WoPage;
import org.dream.www.common.exception.WoResultCode;
import org.dream.www.sys.dto.UserDto;
import org.dream.www.sys.service.RoleService;
import org.dream.www.sys.service.UserService;
import org.dream.www.sys.util.SysConstant;
import org.dream.www.sys.vo.WoDataTable;


@Controller
@Transactional
@RequestMapping("/sys/user")
@SessionAttributes({SysConstant.SESSION_ROLE_LIST,SysConstant.SESSION_USER_UPDATE_DATA})
public class UserController {


	@Resource
	private UserService userService;

	@Resource
	private RoleService roleService;

	/**
	 * @param draw DataTable控件请求列表数据的顺序号
	 * @param start 页第一行数据的索引，0开始
	 * @param length 页最大行数
	 * @param search 查询字符串，对登录名进行模糊匹配
	 * @param orderType 创建时间的排序方式
	 * @return
	 */
	@GetMapping("/list")
	@ResponseBody
	WoDataTable<UserDto> getList(Integer draw, Long start, Long length, 
			@RequestParam("search[value]") String search,
			@RequestParam("order[0][dir]") String orderType) {
		// service返回WoPage数据
		WoPage<UserDto> data = userService.getPageData (start, length, search, orderType);
		// WoPage转化为WoDataTable，返回给前端
		WoDataTable<UserDto> t = new WoDataTable<UserDto>(data, draw);
		return t;
	}


	@GetMapping("/create")
	String create (Map<String, Object>map) {
		// 设置工具区需要include的jsp路径
		map.put(SysConstant.SESSION_USER_TABLE_URL, "user/create.jsp");

		
		return "sysindex";
	}

	@PostMapping("/create")
	String create (UserDto u, Map<String, Object>map) {


		userService.create(u);
		// 返回首页
		return "redirect:/";
	}

	@PostMapping("/delete")
	@ResponseBody
	WoResultCode delete (int[] id) {


		try {
			userService.delete (id);
			return WoResultCode.getSuccessCode();
		} catch (Exception e) {
			return WoResultCode.getUnknownCode();
		}
	}
	@GetMapping("/update")
	String update(Integer id,Map<String, Object> map) {

		map.put(SysConstant.SESSION_USER_UPDATE_DATA, userService.findById(id));
		map.put(SysConstant.SESSION_USER_TABLE_URL, "user/update.jsp");
		return "sysindex";


	}

	@PostMapping("/update")
	String update(UserDto u,Map<String, Object> map) {
		try {
			map.remove(SysConstant.SESSION_UPDATE_ERROR);
			userService.update(u);
			return "sysindex";
		} catch (Exception e) {
			map.put(SysConstant.SESSION_UPDATE_ERROR, e);
			map.put(SysConstant.SESSION_USER_TABLE_URL, "user/update.jsp");
			return "sysindex";
		}
		

	}


}
