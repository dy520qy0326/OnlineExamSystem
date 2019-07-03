package org.dream.www.sys.dto;

import java.util.ArrayList;
import java.util.List;

import org.dream.www.sys.po.Dictionary;
import org.dream.www.sys.po.User;

/** * @author 作者 E-mail:
 * @date 创建时间：2019年6月27日 上午11:24:21 
 * @version 1.0 
 * @parameter 
 * @since 
 * @return 
 */
public class DictionaryDto {
	
	/**
	 * ID
	 */
	private Integer id;
	private String name;
	private String type;
	private String value;
	private String description;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public DictionaryDto(Dictionary po) {
		
		this.id = po.getId();
		this.name = po.getName();
		this.type = po.getType();
		this.value = po.getValue();
		this.description = po.getDescription();
			
	}
	
	public Dictionary CreatePo() {
		
		Dictionary d = new Dictionary();
		d.setId(id);
		d.setName(name);
		d.setType(type);
		d.setValue(value);
		d.setDescription(description);
		return d;
		
	}
	
	/**
	 * @param pos
	 * @return
	 */
	public static List<DictionaryDto> getDtos (List<Dictionary> pos) {
		List<DictionaryDto> dtos = new ArrayList<DictionaryDto>();
		for (Dictionary po : pos) {
			dtos.add(new DictionaryDto(po));
		}
		return dtos;
	}

	public DictionaryDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
