package org.dream.www.exam.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.dream.www.exam.po.Paper;

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


    public PaperDto(Paper po) {
        this.id = po.getId();
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


    public static List<PaperDto> getDtos(List<Paper> content) {
        List<PaperDto> dtos = new ArrayList<PaperDto>();
        for (Paper po:content){
            dtos.add(new PaperDto(po));
        }
        return dtos;
    }
}
