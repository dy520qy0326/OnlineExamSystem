package org.dream.www.sys.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.dream.www.sys.po.Permission;


public interface PermissionRepository extends JpaRepository<Permission, Integer> {

	/**
	 * 查询用户有权限的菜单
	 * @param userId
	 * @return
	 */
	@Query(value = "select p.* from sys_permission p "
			+ "LEFT JOIN sys_role_permission rp on (p.id = rp.permission_id) "
			+ "LEFT JOIN sys_user_role ur on (rp.role_id = ur.role_id) "
			+ "where ur.user_id = ?1 and p.type = '1'", nativeQuery = true)
	List<Permission> findAllMenusByUserId(Integer userId);

	Page<Permission> findAllByNameLike(String string, Pageable p);

	Page<Permission> findAllByOrderByLevelDesc(Pageable p);

	Page<Permission> findAllByOrderByLevelAsc(Pageable p);



}
