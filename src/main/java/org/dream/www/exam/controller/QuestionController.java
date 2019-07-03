package org.dream.www.exam.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import org.dream.www.common.entity.WoPage;
import org.dream.www.common.exception.WoResultCode;
import org.dream.www.common.util.WoJsonUtil;
import org.dream.www.exam.dto.QuestionDto;
import org.dream.www.exam.service.QuestionService;
import org.dream.www.sys.util.SysConstant;
import org.dream.www.sys.vo.WoDataTable;
import org.dream.www.sys.vo.WoSelectorParams;
import org.dream.www.sys.vo.WoUser;


@Controller
@Transactional
@RequestMapping("/exam/question")
public class QuestionController {

    @Resource
    private QuestionService questionService;
    
   

    @GetMapping("/list")
    @ResponseBody
    WoDataTable<QuestionDto> getList(Integer draw, Long start, Long length,
                        @RequestParam("search[value]") String search,
                        @RequestParam("order[0][dir]") String orderType) {
        WoPage<QuestionDto> data =questionService.getPageDate(start, length, search, orderType);

        WoDataTable<QuestionDto> t = new WoDataTable<QuestionDto>(data, draw);
        // System.out.println(t);
        return t;

    }
    
    
	
	
	
	@PostMapping("/delete")
	@ResponseBody
	WoResultCode delete (int[] id) {


		try {
			questionService.delete (id);
			return WoResultCode.getSuccessCode();
		} catch (Exception e) {
			return WoResultCode.getUnknownCode();
		}
	}

	@GetMapping("/update")
	String update(Integer id,Map<String, Object> map) {

		map.put(SysConstant.SESSION_USER_UPDATE_DATA, questionService.findById(id));
		map.put(SysConstant.SESSION_USER_TABLE_URL, "question/update.jsp");
		return "sysindex";


	}

	@PostMapping("/update")

	String update(QuestionDto k,MultipartFile imageFile, Map<String, Object>map, HttpServletRequest req) {
		try {
			map.remove(SysConstant.SESSION_UPDATE_ERROR);
			questionService.update(k,imageFile, req.getServletContext().getRealPath("/"));
			map.put(SysConstant.SESSION_USER_TABLE_URL, "question/questiontable.jsp");
			return "sysindex";

		} catch (Exception e) {
			map.put(SysConstant.SESSION_UPDATE_ERROR, e);
			map.put(SysConstant.SESSION_USER_TABLE_URL, "question/update.jsp");
			return "sysindex";
		}


	}
	
	@GetMapping("/create")
	String create (Map<String, Object>map) {
		// 设置工具区需要include的jsp路径
		map.put(SysConstant.SESSION_USER_TABLE_URL, "question/create.jsp");

		return "sysindex";
	}

	@PostMapping("/create")
	String create (QuestionDto q, MultipartFile imageFile, Map<String, Object>map, HttpServletRequest req,HttpSession session ) {

		WoUser u = (WoUser) session.getAttribute(SysConstant.SESSION_USER);
		q.setCreateUserId(Integer.parseInt(u.getId()));
		Integer id = questionService.create(q, imageFile, req.getServletContext().getRealPath("/"));
		
		
		// 返回首页
		return "redirect:/exam/question/update?id="+id;
	}
	
	@PostMapping("/selector")
	String getSelector(WoSelectorParams param,Map<Object, String> map) {

		map.put("selectorParams", WoJsonUtil.toString(param));
		return "question/selector";

	}
	
	
}
