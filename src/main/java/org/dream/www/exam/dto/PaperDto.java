package org.dream.www.exam.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.dream.www.exam.po.Paper;
import org.dream.www.exam.po.Question;

import java.util.*;

public class PaperDto {


    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 总分数
     */
    private Double score;

    /**
     * 分钟数
     */
    private Integer minutes;


    /**
     * 创建时间
     */
    private Date createTime;
    private Integer createUserId;



    private List<Question> qList;

    Map<Integer, Integer> qnMap;


    public PaperDto(Paper po) {
        this.id = po.getId();
        this.name = po.getName();
        this.createTime = po.getCreateTime();
        this.score = po.getScore();
        this.minutes = po.getMinutes();
    }

    public PaperDto() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", locale = "zh", timezone = "GMT+8") // 设置时间格式
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public List<Question> getqList() {
        return qList;
    }

    public void setqList(List<Question> qList) {
        this.qList = qList;
    }

    public Map<Integer, Integer> getQnMap() {
        return qnMap;
    }

    public void setQnMap(Map<Integer, Integer> qnMap) {
        this.qnMap = qnMap;
    }


    public static List<PaperDto> getDtos(List<Paper> content) {
        List<PaperDto> dtos = new ArrayList<PaperDto>();
        for (Paper po:content){
            dtos.add(new PaperDto(po));
        }
        return dtos;
    }

    public static PaperDto creqnMap(PaperDto dto){
        if (dto.getqList()!=null){

            List<Question> questionList = dto.getqList();

            Map<Integer, Integer> qnMap = new HashMap<>();

            for (Question q:questionList
                 ) {

                if (qnMap.containsKey(q.getKnowledge().getId())){


                 qnMap.put(q.getKnowledge().getId(),qnMap.get(q.getKnowledge().getId())+1);

                }else{

                    qnMap.put(q.getKnowledge().getId(),1);

                }

            }
            dto.setQnMap(qnMap);



        }
        return dto;
    }

    public Paper createPo() {

       Paper po = new Paper();
       po.setId(this.id);
       po.setMinutes(this.minutes);
       po.setName(this.name);

       return  po;


    }
}
