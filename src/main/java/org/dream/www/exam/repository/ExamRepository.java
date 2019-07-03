package org.dream.www.exam.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import org.dream.www.exam.po.Exam;


public interface ExamRepository extends CrudRepository<Exam, Integer> {

	Page<Exam> findAllByCreateUserId(Integer id, Pageable p);

	Page<Exam> findAllByCreateUserIdAndNameLike(Integer id, String string, Pageable p);

}
