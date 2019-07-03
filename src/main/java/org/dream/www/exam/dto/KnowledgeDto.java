package org.dream.www.exam.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dream.www.common.util.WoUtil;
import org.dream.www.exam.po.Knowledge;

public class KnowledgeDto {
    /**
     * ID
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    /**
     * 编号
     */
    private String no;

    /**
     * 描述
     */
    private String description;

    /**
     * 上级知识点
     */
    private String parentId = "";
    
    private String parentName="";

    /**
     * 用户id，多个以逗号隔开
     */
    private String userId = "";
    /**
     * 用户name，多个以逗号隔开
     */
    private String userName = "";




    public KnowledgeDto() {

    }



    public KnowledgeDto(Knowledge k) {
        super();
        this.id = k.getId();
        this.name = k.getName();
        this.type = k.getType();
        this.no = k.getNo();
        this.description = k.getDescription();
        if(k.getParent()!=null) {
        	 this.parentId=k.getParent().getId().toString();
             this.parentName=k.getParent().getName().toString();
        }else {
        	
        	this.parentId="";
            this.parentName="";
        }
        if (k.getCreateUser()!=null) {
			this.userId=k.getCreateUser().getId().toString();
			this.userName=k.getCreateUser().getLoginName();
		}
       
       
    }


    public static  List<KnowledgeDto> getDtos(List<Knowledge> pos){
        List<KnowledgeDto> dtos =new ArrayList<KnowledgeDto>();
        for (Knowledge po : pos) {
            dtos.add(new KnowledgeDto(po));
            
        }
        return dtos;
    }
    public Knowledge createPo() {

    	Knowledge k = new Knowledge();
		
    	if (!WoUtil.isEmpty(id)) {
			k.setId(id);
		}
		k.setName(name);
		k.setDescription(description);
		k.setType(type);
		k.setNo(no);
		k.setCreateTime(new Date());
		
		
		Knowledge k2 = new Knowledge();
		if (!WoUtil.isEmpty(parentId)) {
			k2.setId(Integer.parseInt(parentId.toString()));
			k.setParent(k2);
		}else {
			k.setParent(null);
		}
		
		
		return k;

	}

    /**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}



	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}



	/**
	 * @return the parentName
	 */
	public String getParentName() {
		return parentName;
	}



	/**
	 * @param parentName the parentName to set
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
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
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getNo() {
        return no;
    }
    public void setNo(String no) {
        this.no = no;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getParent_id() {
        return parentId;
    }
    public void setParent_id(String parentId) {
        this.parentId = parentId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

}
