package org.dream.www.exam.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.dream.www.exam.po.AnswerPaper;




public interface AnswerPaperRepository extends JpaRepository<AnswerPaper, Integer> {

	
	
	
	Page<AnswerPaper> findAllByStudentId(Integer studentId, Pageable p);

	Page<AnswerPaper> findAllByStudentIdAndPaperNameLike(Integer userId, String string, Pageable p);

}
