package org.dream.www.sys.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import org.dream.www.common.entity.WoPage;
import org.dream.www.common.util.WoUtil;
import org.dream.www.sys.dto.DictionaryDto;
import org.dream.www.sys.exception.SysException;
import org.dream.www.sys.po.Dictionary;
import org.dream.www.sys.po.User;
import org.dream.www.sys.repository.DictionaryRepository;
import org.dream.www.sys.service.DictionaryService;
import org.dream.www.sys.util.SysConstant;

/** * @author 作者 E-mail:
 * @date 创建时间：2019年6月27日 下午4:56:02 
 * @version 1.0 
 * @parameter 
 * @since 
 * @return 
 */
@Service
@Transactional
public class DictionaryServiceImpl implements DictionaryService {
	
	@Resource 
	private DictionaryRepository dictionaryRepository;

	@Override
	public WoPage<DictionaryDto> getPageData(Long start, Long length, String search, String orderType) {
		// 分页数据
		Pageable p = PageRequest.of(start.intValue() / length.intValue(), length.intValue(),
				"desc".equals(orderType) ? Direction.DESC : Direction.ASC, "id");
		Page<Dictionary> data = null;
		// 判断查询字符串是否为空
		if (WoUtil.isEmpty(search)) {
			data = dictionaryRepository.findAll(p);
		} else {
			data = dictionaryRepository.findAllByNameLike("%" + search + "%", p);
		}
		// 将po的分页数据，转化为dto的分页数据
		return new WoPage<DictionaryDto>(DictionaryDto.getDtos(data.getContent()), data.getTotalElements());
	}

	@Override
	public void create(DictionaryDto d) {
		try {
			Dictionary po = d.CreatePo();
			dictionaryRepository.save(po);
		} catch (Exception e) {
			throw new SysException(SysConstant.ERR_UPDATE_KNOWLEDGE);
		}
		
		
	}

	@Override
	public DictionaryDto findById(Integer id) {
		Dictionary po = dictionaryRepository.findById(id).get();
		DictionaryDto dto = new DictionaryDto(po);
		return dto;
	}

	@Override
	public void update(DictionaryDto d) {
		Dictionary po = d.CreatePo();
		
		try {
			dictionaryRepository.save(po);
		} catch (Exception e) {
			throw new SysException(SysConstant.ERR_UPDATE_KNOWLEDGE);
		}	
		
	}

	@Override
	public void delete(int[] id) {
		if (id==null) {
			throw new SysException(SysConstant.ERR_DELETE_KNOWLEDGE);
		}
		for (int i : id) {
			this.delete(i);
		}
		
	}
	
	@Override
	public void delete(int i) {
		try {
			dictionaryRepository.deleteById(i);
		} catch (Exception e) {
			throw new SysException(SysConstant.ERR_DELETE_KNOWLEDGE);
		}
	
		
	}
}
