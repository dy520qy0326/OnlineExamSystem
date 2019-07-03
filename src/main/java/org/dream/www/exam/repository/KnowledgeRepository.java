package org.dream.www.exam.repository;

import org.dream.www.exam.po.Knowledge;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface KnowledgeRepository extends JpaRepository<Knowledge, Integer> {

    Page<Knowledge> findAllByNameLike(String string, Pageable p);

	/**
	 * 根据知识点获取试题的分布数据
	 * @return
	 */
	@Query(value = "select count(*),k.name from exam_question q "
			+ "LEFT JOIN exam_knowledge k on (q.knowledge_id = k.id) "
			+ "GROUP BY knowledge_id", nativeQuery = true)// 使用sql
	List<Object[]> findQuestionData();
}
