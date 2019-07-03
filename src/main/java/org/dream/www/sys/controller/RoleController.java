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
import org.dream.www.common.util.WoJsonUtil;
import org.dream.www.sys.dto.RoleDto;
import org.dream.www.sys.service.RoleService;
import org.dream.www.sys.util.SysConstant;
import org.dream.www.sys.vo.WoDataTable;
import org.dream.www.sys.vo.WoSelectorParams;


@Controller
@Transactional
@RequestMapping("/sys/role")
@SessionAttributes(SysConstant.SESSION_USER_UPDATE_DATA)
public class RoleController {

	public RoleController() {
		// TODO Auto-generated constructor stub
	}

	@Resource
	private RoleService roleService ;

	@GetMapping("/list")
	@ResponseBody
	WoDataTable<RoleDto> getList(Integer draw, Long start, Long length, 
			@RequestParam("search[value]") String search,
			@RequestParam("order[0][dir]") String orderType) {
		// service返回WoPage数据
		WoPage<RoleDto> data = roleService.getPageData (start, length, search, orderType);
		// WoPage转化为WoDataTable，返回给前端
		WoDataTable<RoleDto> t = new WoDataTable<RoleDto>(data, draw);
		return t;
	}

	@PostMapping("/selector")
	String getSelector(WoSelectorParams param,Map<Object, String> map) {

		map.put("selectorParams", WoJsonUtil.toString(param));
		return "role/selector";

	}
	@GetMapping("/selector/list")
	@ResponseBody
	WoDataTable<RoleDto> getSelectorList(Integer draw, Long start, Long length, 
			@RequestParam("search[value]") String search,
			@RequestParam("order[0][dir]") String orderType) {
		// service返回WoPage数据
		WoPage<RoleDto> data = roleService.getPageData (start, length, search, orderType);
		// WoPage转化为WoDataTable，返回给前端
		WoDataTable<RoleDto> t = new WoDataTable<RoleDto>(data, draw);
		return t;
	}

	@GetMapping("/create")
	String create(Map<Object, String> map) {
		map.put(SysConstant.SESSION_USER_TABLE_URL, "role/create.jsp");

		return "sysindex";

	}
	@PostMapping("/create")
	String create (RoleDto r, Map<String, Object>map) {


		roleService.create(r);
		// 返回首页
		return "redirect:/";
	}

	@PostMapping("/delete")
	@ResponseBody
	WoResultCode delete (int[] id) {


		try {
			roleService.delete (id);
			return WoResultCode.getSuccessCode();
		} catch (Exception e) {
			return WoResultCode.getUnknownCode();
		}
	}

	@GetMapping("/update")
	String update(Integer id,Map<String, Object> map) {

		map.put(SysConstant.SESSION_USER_UPDATE_DATA, roleService.findById(id));
		map.put(SysConstant.SESSION_USER_TABLE_URL, "role/update.jsp");
		return "sysindex";


	}

	@PostMapping("/update")
	String update(RoleDto r,Map<String, Object> map) {
		try {
			map.remove(SysConstant.SESSION_UPDATE_ERROR);
			roleService.update(r);
			return "sysindex";
		} catch (Exception e) {
			map.put(SysConstant.SESSION_UPDATE_ERROR, e);
			map.put(SysConstant.SESSION_USER_TABLE_URL, "role/update.jsp");
			return "sysindex";
		}


	}
}
