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
import org.dream.www.sys.dto.PermissionDto;
import org.dream.www.sys.exception.SysException;
import org.dream.www.sys.po.Permission;
import org.dream.www.sys.repository.PermissionRepository;
import org.dream.www.sys.service.PermissionService;
import org.dream.www.sys.util.SysConstant;

/** * @author 作者 E-mail:
 * @date 创建时间：2019年6月25日 下午8:41:43 
 * @version 1.0 
 * @parameter 
 * @since 
 * @return 
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

	@Resource
	private PermissionRepository permissionRepository;
	@Override
	public WoPage<PermissionDto> getPageData(Long start, Long length, String search, String orderType) {
		// 分页数据
		Pageable p = PageRequest.of(start.intValue() / length.intValue(), length.intValue(),
				"desc".equals(orderType) ? Direction.DESC : Direction.ASC, "level");
		Page<Permission> data = null;
		// 判断查询字符串是否为空
		if (WoUtil.isEmpty(search)) {
			if ("desc".equalsIgnoreCase(orderType)) {
				data = permissionRepository.findAllByOrderByLevelDesc(p);
			}else {
				data = permissionRepository.findAllByOrderByLevelAsc(p);
			}
			
		} else {
			data = permissionRepository.findAllByNameLike("%" + search + "%", p);
		}
		// 将po的分页数据，转化为dto的分页数据
		return new WoPage<PermissionDto>(PermissionDto.getDtos(data.getContent()), data.getTotalElements());
	}
	@Override
	public void create(PermissionDto p) {
		
		Permission po = p.createPo();
		try {
			permissionRepository.save(po);
		} catch (Exception e) {
			throw new SysException(SysConstant.ERR_CREATE_PERMISSION);
		}
	}
	@Override
	public PermissionDto findById(Integer id) {
		
		Permission po = permissionRepository.findById(id).get();
		return  new PermissionDto(po);
		
	}
	@Override
	public void update(PermissionDto r) {
		
		Permission po = r.createPo();
		try {
			permissionRepository.save(po);
		} catch (Exception e) {
			throw new SysException(SysConstant.ERR_UPDATE_ROLE);
		}
		
		
	}
	@Override
	public void delete(int[] id) {

		if (id==null) {
			throw new SysException(SysConstant.ERR_DELETE_PERMISSION);
		}
		for (int i : id) {
			this.delete(i);
		}
	}


	public void delete(int i) {

		try {
			permissionRepository.deleteById(i);
		} catch (Exception e) {
			throw new SysException(SysConstant.ERR_DELETE_PERMISSION);
		}
	}



}
