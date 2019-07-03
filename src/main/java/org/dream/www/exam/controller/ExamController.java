package org.dream.www.exam.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
import org.dream.www.exam.dto.ExamDto;
import org.dream.www.exam.service.ExamService;
import org.dream.www.sys.util.SysConstant;
import org.dream.www.sys.vo.WoDataTable;
import org.dream.www.sys.vo.WoSelectorParams;
import org.dream.www.sys.vo.WoUser;


@Controller
@Transactional
@RequestMapping("/exam/exam")
public class ExamController {

    @Resource
    private ExamService examService;
    
   

    @GetMapping("/list")
    @ResponseBody
    WoDataTable<ExamDto> getList(Integer draw, Long start, Long length,HttpSession session,
                        @RequestParam("search[value]") String search,
                        @RequestParam("order[0][dir]") String orderType) {
    	
    	WoUser u = (WoUser) session.getAttribute(SysConstant.SESSION_USER);
        WoPage<ExamDto> data =examService.getPageDate(start, length, search, orderType,u.getId());

        WoDataTable<ExamDto> t = new WoDataTable<ExamDto>(data, draw);
        // System.out.println(t);
        return t;

    }
	
	@PostMapping("/delete")
	@ResponseBody
	WoResultCode delete (int[] id) {


		try {
			examService.delete (id);
			return WoResultCode.getSuccessCode();
		} catch (Exception e) {
			return WoResultCode.getUnknownCode();
		}
	}

	@GetMapping("/update")
	String update(Integer id,Map<String, Object> map) {

		map.put(SysConstant.SESSION_USER_UPDATE_DATA, examService.findById(id));
		map.put(SysConstant.SESSION_USER_TABLE_URL, "exam/update.jsp");
		return "sysindex";


	}

	@PostMapping("/update")
	@ResponseBody
	WoResultCode update(ExamDto ex, Map<String, Object>map) {
		try {
			map.remove(SysConstant.SESSION_UPDATE_ERROR);
			examService.update(ex);
			return WoResultCode.getSuccessCode();
		} catch (Exception e) {
			map.put(SysConstant.SESSION_UPDATE_ERROR, e);
			map.put(SysConstant.SESSION_USER_TABLE_URL, "exam/update.jsp");
			return WoResultCode.getUnknownCode();
		}


	}
	
	@GetMapping("/create")
	String create (Map<String, Object>map) {
		// 设置工具区需要include的jsp路径
		map.put(SysConstant.SESSION_USER_TABLE_URL, "exam/create.jsp");

		return "sysindex";
	}

	@PostMapping("/create")
	String create (ExamDto ex ,HttpSession session ) {

		WoUser u = (WoUser) session.getAttribute(SysConstant.SESSION_USER);
		ex.setCreateUserId(u.getId());  
		
		
		examService.create(ex);
		// 返回首页
		return "sysindex";
	}
	
	
	
	@GetMapping("/startexam")
	String startexam (Map<String, Object>map,Integer id) {
		// 设置工具区需要include的jsp路径
		examService.startexam(id);

		return "sysindex";
	}
	@GetMapping("/endexam")
	String endtexam (Map<String, Object>map,Integer id) {
		// 设置工具区需要include的jsp路径
		examService.endtexam(id);

		return "sysindex";
	}
	
	
}
