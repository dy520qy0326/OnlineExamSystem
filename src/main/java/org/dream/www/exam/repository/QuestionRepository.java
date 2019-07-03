package org.dream.www.exam.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import org.dream.www.exam.po.Question;


public interface QuestionRepository extends CrudRepository<Question, Integer> {

	Page<Question> findAll(Pageable p);

	Page<Question> findAllByNameLike(String string, Pageable p);

}
