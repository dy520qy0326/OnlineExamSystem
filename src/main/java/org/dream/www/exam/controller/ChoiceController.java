package org.dream.www.exam.controller;

import java.util.Map;

import javax.annotation.Resource;

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
import org.dream.www.exam.dto.ChoiceDto;
import org.dream.www.exam.service.ChoiceeService;
import org.dream.www.sys.util.SysConstant;
import org.dream.www.sys.vo.WoDataTable;
import org.dream.www.sys.vo.WoSelectorParams;


@Controller
@RequestMapping("/exam/choice")
@SessionAttributes(SysConstant.SESSION_CHOICE_CREATE_QUESTIONID)
public class ChoiceController {

	@Resource
	private ChoiceeService choiceService;

	@GetMapping("/listofquestion")
	@ResponseBody
	WoDataTable<ChoiceDto> getList(Integer draw, Long start, Long length,String questionId,
								   @RequestParam("search[value]") String search,
								   @RequestParam("order[0][dir]") String orderType) {

		Integer iqId=Integer.parseInt(questionId);
		WoPage<ChoiceDto> data =choiceService.getPageDate(start, length, search, orderType,iqId);

		WoDataTable<ChoiceDto> t = new WoDataTable<ChoiceDto>(data, draw);
		// System.out.println(t);
		return t;

	}

	@GetMapping("/list")
	@ResponseBody
	WoDataTable<ChoiceDto> getAllList(Integer draw, Long start, Long length,
									  @RequestParam("search[value]") String search,
									  @RequestParam("order[0][dir]") String orderType) {

		WoPage<ChoiceDto> data =choiceService.getPageDate1(start, length, search, orderType);

		WoDataTable<ChoiceDto> t = new WoDataTable<ChoiceDto>(data, draw);
		// System.out.println(t);
		return t;

	}



	@PostMapping("/delete")
	@ResponseBody
	WoResultCode delete (int[] id) {


		try {
			choiceService.delete (id);
			return WoResultCode.getSuccessCode();
		} catch (Exception e) {
			return WoResultCode.getUnknownCode();
		}
	}

	@GetMapping("/update")
	String update(Integer id,Map<String, Object> map) {

		map.put(SysConstant.SESSION_USER_UPDATE_DATA, choiceService.findById(id));
		map.put(SysConstant.SESSION_USER_TABLE_URL, "choice/update.jsp");
		return "choice/update";


	}

	@PostMapping("/update")
	@ResponseBody
	WoResultCode update(ChoiceDto k,Map<String, Object> map) {
		try {
			map.remove(SysConstant.SESSION_UPDATE_ERROR);
			choiceService.update(k);
			return WoResultCode.getSuccessCode();
		} catch (Exception e) {
			map.put(SysConstant.SESSION_UPDATE_ERROR, e);
			map.put(SysConstant.SESSION_USER_TABLE_URL, "choice/update.jsp");
			return WoResultCode.getSuccessCode();
		}


	}

	@GetMapping("/create")
	String create (Integer questionId,Map<String, Object>map) {

		// 设置工具区需要include的jsp路径
		map.put(SysConstant.SESSION_CHOICE_CREATE_QUESTIONID, questionId);
		map.put(SysConstant.SESSION_USER_TABLE_URL, "choice/create.jsp");

		return "choice/create";
	}

	@PostMapping("/create")
	@ResponseBody
	WoResultCode create (ChoiceDto k, Map<String, Object>map) {

		try {
			choiceService.create(k);
			return WoResultCode.getSuccessCode();

		} catch (Exception e) {
			return WoResultCode.getUnknownCode();
		}

	}

	@PostMapping("/selector")
	String getSelector(WoSelectorParams param,Map<Object, String> map) {

		map.put("selectorParams", WoJsonUtil.toString(param));
		return "choice/selector";

	}

	@RequestMapping("/up")
	@ResponseBody
	WoResultCode upChoice(Integer id) {
		try {
			choiceService.upChoice(id);
			return WoResultCode.getSuccessCode();
		} catch (Exception e) {
			return WoResultCode.getUnknownCode();
		}




	}

	@RequestMapping("/down")
	@ResponseBody
	WoResultCode downChoice(Integer id) {
		try {
			choiceService.downChoice(id);
			return WoResultCode.getSuccessCode();
		} catch (Exception e) {
			return WoResultCode.getUnknownCode();
		}




	}
}
