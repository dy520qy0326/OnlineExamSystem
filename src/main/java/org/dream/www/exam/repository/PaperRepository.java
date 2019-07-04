package org.dream.www.exam.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import org.dream.www.exam.po.Paper;


public interface PaperRepository extends CrudRepository<Paper, Integer> {

    Page<Paper> findAll(Pageable p);

    Page<Paper> findAllByNameLike(String s, Pageable p);
}
