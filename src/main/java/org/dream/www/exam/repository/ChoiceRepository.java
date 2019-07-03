package org.dream.www.exam.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.dream.www.exam.po.Choice;


public interface ChoiceRepository extends JpaRepository<Choice, Integer> {

	Page<Choice> findAllByQuestionId(Integer questionId, Pageable p);

	Page<Choice> findAll(Pageable p);

	Page<Choice> findAllByQuestionIdAndDescriptionLike(Integer questionId, String string, Pageable p);

	Page<Choice> findAllByDescriptionLike(String string, Pageable p);

	List<Choice> findAllByQuestionId(int questionId);
	void  deleteAllByQuestionId(int questionId);
}
