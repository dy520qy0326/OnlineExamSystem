package org.dream.www.sys.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import org.dream.www.sys.po.Role;


public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String name);

	Page<Role> findAllByNameLike(String string, Pageable p);

}
