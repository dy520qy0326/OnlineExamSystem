package org.dream.www.exam.vo;

import java.io.Serializable;

public class WoKnowledgeQuestion implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1294290261937031280L;


	/**
	 *知识点对应的id 
	 */
	private Integer knowledgeId;
	

	/**
	 *知识点名称 
	 */
	private String name;
	
	
	/**
	 *知识点对应试题数量 
	 */
	private Integer sum;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getSum() {
		return sum;
	}


	public void setSum(Integer sum) {
		this.sum = sum;
	}
	public Integer getKnowledgeId() {
		return knowledgeId;
	}


	public void setKnowledgeId(Integer knowledgeId) {
		this.knowledgeId = knowledgeId;
	}


}