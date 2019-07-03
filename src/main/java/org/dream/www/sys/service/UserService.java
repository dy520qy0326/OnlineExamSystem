package org.dream.www.sys.service;

import java.util.List;

import org.dream.www.common.entity.WoPage;
import org.dream.www.sys.dto.UserDto;
import org.dream.www.sys.po.User;

public interface UserService {

	List<User> findAll();

	void create(UserDto u);

	WoPage<UserDto> getPageData(Long start, Long length, String search, String orderType);

	void delete(int[] id);
	void delete(int i);

	UserDto findById(Integer id);

	void update(UserDto u);
	
	
}
