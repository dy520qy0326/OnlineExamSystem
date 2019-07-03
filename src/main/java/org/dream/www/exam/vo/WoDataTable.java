package org.dream.www.exam.vo;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.dream.www.common.entity.WoPage;

/**
 * DataTable控件列表数据对象
 * @author cailei
 *
 * @param <T>
 */
public class WoDataTable<T> {
	
	private final static Logger LOG = LogManager.getLogger(WoDataTable.class);
	
	private Integer draw = 0;
	
	/**
	 * 总行数
	 */
	private Long recordsTotal = 0L;
	
	/**
	 * 暂时当作总行数处理
	 */
	private Long recordsFiltered = 0L;
	
	/**
	 * 分页数据
	 */
	private List<T> data = new ArrayList<T>();

	public WoDataTable () {}
	
	public WoDataTable (WoPage<T> page, Integer draw) {
		recordsTotal = page.getResults();
		recordsFiltered = page.getResults();
		data = page.getRows();
		this.draw = draw;
	}
	
	public Integer getDraw() {
		return draw;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(Long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Long getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(Long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
