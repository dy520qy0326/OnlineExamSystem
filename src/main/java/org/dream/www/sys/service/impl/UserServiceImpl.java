package org.dream.www.sys.service.impl;


import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import org.dream.www.common.entity.WoPage;
import org.dream.www.common.util.WoUtil;
import org.dream.www.sys.dto.UserDto;
import org.dream.www.sys.exception.SysException;
import org.dream.www.sys.po.User;
import org.dream.www.sys.repository.UserRepository;
import org.dream.www.sys.service.UserService;
import org.dream.www.sys.util.SysConstant;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserRepository userRepository;
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override

	public void create(UserDto u) {
		User po = u.createPO();
		userRepository.save(po);
		// 密码加密
		po.setPassword(WoUtil.getMD5(po.getId().toString(), u.getPassword()));
		 userRepository.save(po);
	}
	
	@Override
	public WoPage<UserDto> getPageData(Long start, Long length, String search, String orderType) {
		// 分页数据
		Pageable p = PageRequest.of(start.intValue() / length.intValue(), length.intValue(),
				"desc".equals(orderType) ? Direction.DESC : Direction.ASC, "createTime");
		Page<User> data = null;
		// 判断查询字符串是否为空
		if (WoUtil.isEmpty(search)) {
			data = userRepository.findAll(p);
		} else {
			data = userRepository.findAllByLoginNameLike("%" + search + "%", p);
		}
		// 将po的分页数据，转化为dto的分页数据
		return new WoPage<UserDto>(UserDto.getDtos(data.getContent()), data.getTotalElements());
	}

	@Override
	public void delete(int[] id) {
		if (id==null) {
			throw new SysException(SysConstant.ERR_DELETE_USER);
		}
		for (int i : id) {
			this.delete(i);
		}
		
	}
	@Override
	public void delete(int i) {
		try {
			userRepository.deleteById(i);
		} catch (Exception e) {
			throw new SysException(SysConstant.ERR_DELETE_USER);
		}
	
		
	}

	@Override
	public UserDto findById(Integer id) {
		User po = userRepository.findById(id).get();
		UserDto userDto = new UserDto(po);
		return userDto;
	}

	@Override
	public void update(UserDto u) {
		User po = u.createPO();
		
		if (po.getPassword().isEmpty()) {
			try {
				userRepository.save(po);
			} catch (Exception e) {
				throw new SysException(SysConstant.ERR_UPDATE);
			}
		
		}else {
			try {
				po.setPassword(WoUtil.getMD5(po.getId().toString(), po.getPassword()));
				userRepository.save(po);
			} catch (Exception e) {
				throw new SysException(SysConstant.ERR_UPDATE);
			}
			
		}
	}

}
