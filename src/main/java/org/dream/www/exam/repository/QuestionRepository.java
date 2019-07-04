package org.dream.www.exam.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.dream.www.exam.po.Question;

import java.util.List;


public interface QuestionRepository extends CrudRepository<Question, Integer> {

	Page<Question> findAll(Pageable p);

	Page<Question> findAllByNameLike(String string, Pageable p);

    List<Question> findAllByKnowledgeId(Integer id);
    @Query(nativeQuery = true,value = "select * from exam_question " +
            "where id " +
            "in(select question_id " +
            "from exam_paper_question " +
            "where paper_id=?1)"
    )
    @Modifying
    List<Question> findAllByPaperId(Integer paperId);
}
