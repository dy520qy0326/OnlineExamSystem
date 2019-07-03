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

import org.dream.www.common.entity.WoPage;
import org.dream.www.common.exception.WoResultCode;
import org.dream.www.common.util.WoJsonUtil;
import org.dream.www.sys.dto.PermissionDto;
import org.dream.www.sys.dto.RoleDto;
import org.dream.www.sys.service.PermissionService;
import org.dream.www.sys.util.SysConstant;
import org.dream.www.sys.vo.WoDataTable;
import org.dream.www.sys.vo.WoSelectorParams;


@Controller
@Transactional
@RequestMapping("/sys/permission")
public class PermissionController {

	public PermissionController() {
		// TODO Auto-generated constructor stub
	}

	@Resource
	private PermissionService permissionService ;

	@GetMapping("/list")
	@ResponseBody
	WoDataTable<PermissionDto> getList(Integer draw, Long start, Long length, 
			@RequestParam("search[value]") String search,
			@RequestParam("order[0][dir]") String orderType) {
		// service返回WoPage数据
		WoPage<PermissionDto> data = permissionService.getPageData (start, length, search, orderType);
		// WoPage转化为WoDataTable，返回给前端
		WoDataTable<PermissionDto> t = new WoDataTable<PermissionDto>(data, draw);
		return t;
	}

	@PostMapping("/selector")
	String getSelector(WoSelectorParams param,Map<Object, String> map) {

		map.put("selectorParams", WoJsonUtil.toString(param));
		return "permission/selector";

	}
	@PostMapping("/selectorparent")
	String getSelectorParent(WoSelectorParams param,Map<Object, String> map) {

		map.put("selectorParams", WoJsonUtil.toString(param));
		return "permission/selector";

	}
	@GetMapping("/selector/list")
	@ResponseBody
	WoDataTable<PermissionDto> getSelectorList(Integer draw, Long start, Long length, 
			@RequestParam("search[value]") String search,
			@RequestParam("order[0][dir]") String orderType) {
		// service返回WoPage数据
		WoPage<PermissionDto> data = permissionService.getPageData (start, length, search, orderType);
		// WoPage转化为WoDataTable，返回给前端
		WoDataTable<PermissionDto> t = new WoDataTable<PermissionDto>(data, draw);
		return t;
	}

	@GetMapping("/create")
	String create(Map<Object, String> map) {
		map.put(SysConstant.SESSION_USER_TABLE_URL, "permission/create.jsp");

		return "sysindex";

	}

	@PostMapping("/create")
	String create (PermissionDto p, Map<String, Object>map) {


		permissionService.create(p);
		// 返回首页
		return "redirect:/";
	}

	@GetMapping("/update")
	String update(Integer id,Map<String, Object> map) {

		map.put(SysConstant.SESSION_USER_UPDATE_DATA, permissionService.findById(id));
		map.put(SysConstant.SESSION_USER_TABLE_URL, "permission/update.jsp");
		return "sysindex";


	}

	@PostMapping("/update")
	String update(PermissionDto p,Map<String, Object> map) {
		try {
			map.remove(SysConstant.SESSION_UPDATE_ERROR);
			permissionService.update(p);
			return "sysindex";
		} catch (Exception e) {
			map.put(SysConstant.SESSION_UPDATE_ERROR, e);
			map.put(SysConstant.SESSION_USER_TABLE_URL, "permission/update.jsp");
			return "sysindex";
		}
	}
	
	@PostMapping("/delete")
	@ResponseBody
	WoResultCode delete (int[] id) {


		try {
			permissionService.delete (id);
			return WoResultCode.getSuccessCode();
		} catch (Exception e) {
			return WoResultCode.getUnknownCode();
		}
	}
}
