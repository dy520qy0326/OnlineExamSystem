package org.dream.www.exam.controller;

import org.dream.www.common.entity.WoPage;
import org.dream.www.common.exception.WoResultCode;
import org.dream.www.exam.dto.KnowledgeDto;
import org.dream.www.exam.dto.PaperDto;
import org.dream.www.exam.po.Paper;
import org.dream.www.exam.service.KnowledgeService;
import org.dream.www.exam.service.PaperService;
import org.dream.www.exam.service.QuestionService;
import org.dream.www.exam.vo.WoKnowledgeQuestion;
import org.dream.www.exam.vo.WoPaperQuestionKnowledge;
import org.dream.www.sys.util.SysConstant;
import org.dream.www.sys.vo.WoDataTable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.*;

@Controller
@Transactional
@RequestMapping("/exam/paper")
@SessionAttributes({SysConstant.SESSION_QUESTION_KNOWLEDGE,SysConstant.SESSION_UPDATE_PAPER_LIST})
public class PaperController<add> {

    @Resource
    private PaperService paperService;

    @Resource
    private KnowledgeService knowledgeService;

    @Resource
    private QuestionService qusetionService;


    @GetMapping("/list")
    @ResponseBody
    WoDataTable<PaperDto> getList(Integer draw, Long start, Long length,
                                      @RequestParam("search[value]") String search,
                                      @RequestParam("order[0][dir]") String orderType) {
        WoPage<PaperDto> data =paperService.getPageDate(start, length, search, orderType);

        WoDataTable<PaperDto> t = new WoDataTable<PaperDto>(data, draw);

        return t;

    }

    @GetMapping("/create")
    String toCreate(Map<String,Object>map){

        map.put(SysConstant.SESSION_USER_TABLE_URL,"paper/create.jsp");

        map.put(SysConstant.SESSION_QUESTION_KNOWLEDGE,this.createkqList());

        return "sysindex";

    }
    /*
     *
     * @Author dengye
     * @Description //TODO
     * @Date  kqMap 知识点及出题量
     * @Param  map 前端返回的name_value集合
     * @return  **.jsp
     **/
    @PostMapping("/create")
    String create(@RequestParam Map<String,Object>map){

         Set<String> key =   map.keySet();
        Map<Integer,Integer> kqMap= new HashMap<>();
        for (String k:key) {

            if (k.startsWith("knowledge_")){
                Integer mk = Integer.parseInt(k.substring(10));

                Integer mv= Integer.parseInt(map.get(k).toString());

                kqMap.put(mk,mv);

            }

        }

        String paperName = map.get("name").toString();
        Integer minutes = Integer.parseInt(map.get("minutes").toString());
        paperService.create(paperName,minutes,kqMap);


        return "redirect:/";
    }

    @PostMapping("/delete")
    @ResponseBody
    WoResultCode delete(Integer[] id){

        try {

            paperService.delete(id);

            return  WoResultCode.getSuccessCode();
        }catch (Exception e){

           return WoResultCode.getUnknownCode();
        }
    }

    @GetMapping("/update")
    String toUpdate(Integer id,Map<String,Object>map){

        List<WoKnowledgeQuestion>kqList = this.createkqList();
        //存储知识点、题目数、所需题目数的VO集合
        List<WoPaperQuestionKnowledge> pqkList = new ArrayList<>();

        PaperDto dto = paperService.findById(id);

        Map<Integer,Integer> qnMap = dto.getQnMap();

        for (WoKnowledgeQuestion kq:kqList
             ) {

            WoPaperQuestionKnowledge pqk = new WoPaperQuestionKnowledge();

           pqk.setKnowledgeId(kq.getKnowledgeId());

           pqk.setKnowledgeName(kq.getName());
           pqk.setQuestionNum(kq.getSum());
           pqk.setQuestionNeed(qnMap.get(kq.getKnowledgeId()));
           pqkList.add(pqk);
        }

        map.put(SysConstant.SESSION_USER_UPDATE_DATA,dto);
        map.put(SysConstant.SESSION_UPDATE_PAPER_LIST,pqkList);


        map.put(SysConstant.SESSION_USER_TABLE_URL,"paper/update.jsp");

        return "sysindex";

    }

    @PostMapping("/update")
    @ResponseBody
    WoResultCode update (@RequestParam Map<String,Object>map,Map<String,Object>ma){

        Set<String> key =   map.keySet();
       PaperDto paperDto =  new PaperDto();
        Map<Integer,Integer> kqMap= new HashMap<>();

        for (String k:key) {

            if (k.startsWith("knowledge_")){
                Integer mk = Integer.parseInt(k.substring(10));

                Integer mv= Integer.parseInt(map.get(k).toString());

                kqMap.put(mk,mv);

            }

        }

        Integer minutes = Integer.parseInt(map.get("minutes").toString());
        paperDto.setMinutes(minutes);

        Integer id = Integer.parseInt(map.get("id").toString());

        paperDto.setId(id);

        String paperName = map.get("name").toString();
        paperDto.setName(paperName);

        try {
            paperService.update(paperDto,kqMap);
            ma.put(SysConstant.SESSION_USER_TABLE_URL,"paper/papertable.jsp");
            return WoResultCode.getSuccessCode();
        }catch (Exception e){

            return WoResultCode.getUnknownCode();

        }


    }
    private List<WoKnowledgeQuestion> createkqList(){

        List<KnowledgeDto> kList=knowledgeService.findAll();

        List<WoKnowledgeQuestion> kqList = new ArrayList<>();

        for (KnowledgeDto k:kList
        ) {
            WoKnowledgeQuestion kq = new WoKnowledgeQuestion();

            kq.setKnowledgeId(k.getId());
            kq.setName(k.getName());

            Integer sum = qusetionService.getSum(k.getId()).size();

            kq.setSum(sum);

            kqList.add(kq);
        }
        return kqList;
    }
}
