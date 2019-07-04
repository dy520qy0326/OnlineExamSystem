package org.dream.www.exam.vo;

import java.io.Serializable;

public class WoPaperQuestionKnowledge implements Serializable {

    private static final long serialVersionUID = 6904561166785719036L;

    public Integer getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(Integer knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }

    public Integer getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
    }

    public Integer getQuestionNeed() {
        return questionNeed;
    }

    public void setQuestionNeed(Integer questionNeed) {
        this.questionNeed = questionNeed;
    }

    private Integer knowledgeId;
    private String  knowledgeName;
    private Integer questionNum;
    private Integer questionNeed;





}
