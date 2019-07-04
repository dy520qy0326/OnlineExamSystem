package org.dream.www.exam.repository;

import org.dream.www.exam.po.PaperQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;




public interface PaperQuestionRepository extends JpaRepository<PaperQuestion, Integer> {
    @Query(nativeQuery = true, value = "insert INTO exam_paper_question "
            + "(question_id, paper_id, score) "
            + "SELECT id, ?2, 1.0 FROM exam_question "
            + "where knowledge_id = ?1 ORDER BY RAND() LIMIT ?3")
    @Modifying
    int insertRandomQuestions(Integer knowledgeId, Integer paperId, Integer questionCount);

    void deleteAllByPaperId(Integer id);
}
