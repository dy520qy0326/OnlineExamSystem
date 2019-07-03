package org.dream.www.common.entity;

import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * 分页数据实体
 * 
 * @author jiaod
 *
 * @param <T>
 */
public class WoPage <T> {
	
	@SuppressWarnings("unused")
	private final static Logger LOG = LogManager.getLogger(WoPage.class);
	
	public WoPage(List<T> rows) {
		super();
		this.rows = rows;
		this.results = Long.valueOf(rows.size());
	}
	
	public WoPage (List<T> rows, Long results) {
		super();
		this.rows = rows;
		this.results = results;
	}
	
	public WoPage() {
	}
	
	/**
	 * 总行数
	 */
	private Long results = 0L;
	
	/**
	 * 页数据
	 */
	private List<T> rows;

	public Long getResults() {
		return results;
	}

	public void setResults(Long results) {
		this.results = results;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
