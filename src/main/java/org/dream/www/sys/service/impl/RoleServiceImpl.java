package org.dream.www.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import org.dream.www.common.entity.WoPage;
import org.dream.www.common.util.WoUtil;
import org.dream.www.sys.dto.RoleDto;
import org.dream.www.sys.exception.SysException;
import org.dream.www.sys.po.Role;
import org.dream.www.sys.repository.RoleRepository;
import org.dream.www.sys.service.RoleService;
import org.dream.www.sys.util.SysConstant;
import org.dream.www.sys.vo.WoRole;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleRepository roleRepository;

	@Override
	public List<WoRole> findAllToWoRole() {
		List<Role> list = roleRepository.findAll();
		List<WoRole> rList = new ArrayList<WoRole>();
		for (Role role : list) {
			WoRole woRole = new WoRole();
			woRole.setId(role.getId());
			woRole.setName(role.getName());
			woRole.setDescription(role.getDescription());
			rList.add(woRole);
		}
		return rList;

	}
	@Override
	public WoPage<RoleDto> getPageData(Long start, Long length, String search, String orderType) {

		// 分页数据
		Pageable p = PageRequest.of(start.intValue() / length.intValue(), length.intValue(),
				"desc".equals(orderType) ? Direction.DESC : Direction.ASC, "id");
		Page<Role> data = null;
		// 判断查询字符串是否为空
		if (WoUtil.isEmpty(search)) {
			data = roleRepository.findAll(p);
		} else {
			data = roleRepository.findAllByNameLike("%" + search + "%", p);
		}
		// 将po的分页数据，转化为dto的分页数据
		return new WoPage<RoleDto>(RoleDto.getDtos(data.getContent()), data.getTotalElements());
	}
	@Override
	public void create(RoleDto r) {
		Role role = r.createPO();
		roleRepository.save(role);
	}
	@Override
	public void delete(int[] id) {

		if (id==null) {
			throw new SysException(SysConstant.ERR_DELETE_ROLE);
		}
		for (int i : id) {
			this.delete(i);
		}
	}


	public void delete(int i) {

		try {
			roleRepository.deleteById(i);
		} catch (Exception e) {
			throw new SysException(SysConstant.ERR_DELETE_ROLE);
		}
	}
	
	@Override
	public RoleDto findById(Integer id) {
		Role po = roleRepository.findById(id).get();
		RoleDto RoleDto = new RoleDto(po);
		return RoleDto;
	}

	@Override
	public void update(RoleDto u) {
		Role po = u.createPO();
		
		
			try {
				roleRepository.save(po);
			} catch (Exception e) {
				throw new SysException(SysConstant.ERR_UPDATE_ROLE);
			}
		
			
		
	}

}
