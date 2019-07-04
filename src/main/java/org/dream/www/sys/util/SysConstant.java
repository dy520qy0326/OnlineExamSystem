package org.dream.www.sys.util;

import org.dream.www.common.exception.WoResultCode;

public interface SysConstant {
	
	String SESSION_USER = "sysUser";
	String SESSION_LOGIN_NAME = "loginName";
	String SESSION_LOGIN_ERROR = "error";
	String SESSION_UPDATE_ERROR = "uperror";
	String SESSION_ROLE_LIST = "rList";
	String SESSION_USER_TABLE_URL = "url";
	String SESSION_USER_UPDATE_DATA = "formData";
	String SESSION_QUESTION_KNOWLEDGE = "kqList";
	String SESSION_CHOICE_CREATE_QUESTIONID = "questionId";
	String SESSION_UPDATE_PAPER_LIST = "pqkList";
	WoResultCode ERR_LOGIN = new WoResultCode(102, "用户名或者密码不正确！");

	WoResultCode ERR_LOGIN_NOT = new WoResultCode(104, "请登录！");

	WoResultCode ERR_LOGIN_DEPT = new WoResultCode(105, "用户未关联部门！");

	WoResultCode ERR_LOGIN_ROLE = new WoResultCode(106, "用户未关联该角色！");
	WoResultCode ERR_DELETE_USER = new WoResultCode(202, "未选择删除对象");
	WoResultCode ERR_UPDATE = new WoResultCode(302, "更新失败，用户名重复");
	WoResultCode ERR_DELETE_ROLE = new WoResultCode(402, "删除失败");
	WoResultCode ERR_UPDATE_ROLE = new WoResultCode(404, "更新失败，名称重复");
	WoResultCode ERR_CREATE_PERMISSION = new WoResultCode(502, "创建失败，分级重复");
	WoResultCode ERR_UPDATE_PERMISSION = new WoResultCode(504, "更新失败，分级重复");
	WoResultCode ERR_DELETE_PERMISSION = new WoResultCode(506, "删除失败");
	WoResultCode ERR_UPDATE_KNOWLEDGE = new WoResultCode(602, "更新失败");
	WoResultCode ERR_CREATE_KNOWLEDGE = new WoResultCode(604, "创建失败"); 
	WoResultCode ERR_DELETE_KNOWLEDGE = new WoResultCode(606, "创建失败"); 
	
	WoResultCode ERR_UPDATE_CHOICE = new WoResultCode(702, "更新失败");
	WoResultCode ERR_CREATE_CHOICE = new WoResultCode(704, "创建失败"); 
	WoResultCode ERR_DELETE_CHOICE = new WoResultCode(706, "创建失败"); 

}
