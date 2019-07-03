package org.dream.www.sys.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import org.dream.www.sys.po.Dictionary;


public interface DictionaryRepository extends CrudRepository<Dictionary, Integer> {

	Page<Dictionary> findAll(Pageable p);

	Page<Dictionary> findAllByNameLike(String string, Pageable p);

}
