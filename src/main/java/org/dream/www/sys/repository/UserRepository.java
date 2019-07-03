package org.dream.www.sys.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.dream.www.sys.po.User;


public interface UserRepository extends JpaRepository<User, Integer> {

	User findByloginName(String loginName);

	Page<User> findAllByLoginNameLike(String string, Pageable p);

}
