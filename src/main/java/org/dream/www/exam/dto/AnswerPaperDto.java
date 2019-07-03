package org.dream.www.exam.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.dream.www.exam.po.AnswerPaper;

/** 
 * @author 作者 邓烨 秦烨
 * @date 创建时间：2019年6月24日 下午5:15:09 
 * @version 1.0 
 * @parameter 
 * @since 
 * @return 
 */
/**
 * @author DYworkgroup
 *
 */
public class AnswerPaperDto {
	
	@Resource
	
	/**
	 * id 答卷id 对应前端编号
	 */
	private Integer id;
	
	/**
	 * studentId 用户id
	 */
	private Integer studentId;
	
	/**
	 * userName 用户姓名
	 */
	private String userName;
	
	/**
	 * userName 用户角色名
	 */
	private String roleName ;
	
	/**
	 * paperName 试卷名
	 */
	private String paperName;
	
	/**
	 * score 分数
	 */
	private Double score;
	
	/**
	 * startTime 开始时间
	 */

	private Date startTime = new Date();

	/**
	 * @return the startTime
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", locale = "zh", timezone = "GMT+8")
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the studentId
	 */
	public Integer getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the paperName
	 */
	public String getPaperName() {
		return paperName;
	}

	/**
	 * @param paperName the paperName to set
	 */
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	/**
	 * @return the score
	 */
	public Double getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(Double score) {
		this.score = score;
	}

	public AnswerPaperDto() {
		
	}
	
	public AnswerPaperDto(AnswerPaper po) {
		
		this.id = po.getId();
		this.studentId = po.getStudent().getId();
		this.userName = po.getStudent().getLoginName();
		this.roleName = po.getStudent().getRoles().get(0).getName();
		this.paperName = po.getPaper().getName();
		this.score = po.getScore();
		this.startTime = po.getStartTime();
		
	}
	
	public static List<AnswerPaperDto> getDtos (List<AnswerPaper> pos) {
		List<AnswerPaperDto> dtos = new ArrayList<AnswerPaperDto>();
		for (AnswerPaper po : pos) {
			dtos.add(new AnswerPaperDto(po));
		}
		return dtos;
	}

}
