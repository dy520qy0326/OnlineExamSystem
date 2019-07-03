package org.dream.www.exam.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.dream.www.exam.po.Knowledge;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.dream.www.common.entity.WoPage;
import org.dream.www.common.exception.WoResultCode;
import org.dream.www.common.util.WoJsonUtil;
import org.dream.www.exam.dto.KnowledgeDto;
import org.dream.www.exam.service.KnowledgeService;
import org.dream.www.sys.util.SysConstant;
import org.dream.www.sys.vo.WoDataTable;
import org.dream.www.sys.vo.WoSelectorParams;


@Controller
@RequestMapping("/exam/knowledge")
public class KnowledgeController {

    @Resource
    private KnowledgeService knowledgeService;

    @GetMapping("/list")
    @ResponseBody
    WoDataTable<KnowledgeDto> getList(Integer draw, Long start, Long length,
                        @RequestParam("search[value]") String search,
                        @RequestParam("order[0][dir]") String orderType) {
        WoPage<KnowledgeDto> data =knowledgeService.getPageDate(start, length, search, orderType);

        WoDataTable<KnowledgeDto> t = new WoDataTable<KnowledgeDto>(data, draw);
        // System.out.println(t);
        return t;

    }
    
    /**
	 * 进入试题统计页面
	 * @return
	 */
	@RequestMapping("/questions")
	public String showQuestions(Map<String,Object> map){
		// Object[]中，第一个元素为试题数量，第二个元素为知识点名称
		List<Object[]> data = this.knowledgeService.getQuestionData();
		// 组装x轴数据和每个坐标的数据
		// x轴
		StringBuffer x = new StringBuffer();
		// 试题数量数组
		StringBuffer numbers = new StringBuffer();
		for (Object[] r : data) {
			if (x.length() > 0) {
				x.append(",");
				numbers.append(",");
			}
			x.append("\"");
			x.append(r[1]);
			x.append("\"");
			numbers.append(r[0]);
		}
		map.put("xAxis", x);
		map.put("data", numbers);
		map.put("url", "knowledge/questions.jsp");
		return "sysindex";
	}
	
	/**
	 * @param map
	 * @return
	 */
	@GetMapping ("/options")
	String getOptions (Map<String, Object>map,Integer selectedId) {
		List<KnowledgeDto> data = this.knowledgeService.getData ();
		KnowledgeDto temp = new KnowledgeDto();
		for (int i = 0;i<data.size()-1;i++){

			if (data.get(i).getId()==selectedId){

				temp = data.get(i);
				data.remove(i);
				data.add(temp);
			}
		}

		if (data.size()>1){

			temp = data.get(0);
			data.set(0,data.get(data.size()-1));
			data.set(data.size()-1,temp);

		}

		map.put("data", data);
		return "knowledge/options";
	}
	
	@PostMapping("/delete")
	@ResponseBody
	WoResultCode delete (int[] id) {


		try {
			knowledgeService.delete (id);
			return WoResultCode.getSuccessCode();
		} catch (Exception e) {
			return WoResultCode.getUnknownCode();
		}
	}

	@GetMapping("/update")
	String update(Integer id,Map<String, Object> map) {

		map.put(SysConstant.SESSION_USER_UPDATE_DATA, knowledgeService.findById(id));
		map.put(SysConstant.SESSION_USER_TABLE_URL, "knowledge/update.jsp");
		return "sysindex";


	}

	@PostMapping("/update")
	String update(KnowledgeDto k,Map<String, Object> map) {
		try {
			map.remove(SysConstant.SESSION_UPDATE_ERROR);
			knowledgeService.update(k);
			return "sysindex";
		} catch (Exception e) {
			map.put(SysConstant.SESSION_UPDATE_ERROR, e);
			map.put(SysConstant.SESSION_USER_TABLE_URL, "knowledge/update.jsp");
			return "sysindex";
		}


	}
	
	@GetMapping("/create")
	String create (Map<String, Object>map) {
		// 设置工具区需要include的jsp路径
		map.put(SysConstant.SESSION_USER_TABLE_URL, "knowledge/create.jsp");

		return "sysindex";
	}

	@PostMapping("/create")
	String create (KnowledgeDto k, Map<String, Object>map) {


		knowledgeService.create(k);
		// 返回首页
		return "redirect:/";
	}
	
	@PostMapping("/selector")
	String getSelector(WoSelectorParams param,Map<Object, String> map) {

		map.put("selectorParams", WoJsonUtil.toString(param));
		return "knowledge/selector";

	}
}
