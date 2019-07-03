package org.dream.www.exam.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.dream.www.common.entity.WoPage;
import org.dream.www.exam.dto.AnswerPaperDto;
import org.dream.www.sys.dto.PermissionDto;
import org.dream.www.sys.service.AnswerPaperService;
import org.dream.www.sys.vo.WoDataTable;

/** 
 * @author 作者 邓烨 秦烨
 * @date 创建时间：2019年6月24日 下午5:15:09 
 * @version 1.0 
 * @parameter 
 * @since 
 * @return 
 */
@Controller
@Transactional
@RequestMapping("/exam/answerpaper")
public class AnswerPaperController {

	@Resource
	private AnswerPaperService answerPaperService;

	/**
	 * @param draw DataTable控件请求列表数据的顺序号
	 * @param start 页第一行数据的索引，0开始
	 * @param length 页最大行数
	 * @param userId 通过用户Id进行查询
	 * @param search 查询字符串，对登录名进行模糊匹配
	 * @param orderType 创建时间的排序方式
	 * @return
	 */
private final static Logger LOG = LogManager.getLogger(AnswerPaperController.class);

	@GetMapping("/list")
	@ResponseBody
	WoDataTable<AnswerPaperDto> getList(Integer draw, Long start, Long length, String userId,
			@RequestParam("search[value]") String search,
			@RequestParam("order[0][dir]") String orderType) {
		Integer iuserId = Integer.parseInt(userId);
		// service返回WoPage数据
		WoPage<AnswerPaperDto> data = answerPaperService.getPageData (start, length, search, orderType,iuserId);
		// WoPage转化为WoDataTable，返回给前端
		WoDataTable<AnswerPaperDto> t = new WoDataTable<AnswerPaperDto>(data, draw);

		return t;
	}

	public AnswerPaperController() {}

}
