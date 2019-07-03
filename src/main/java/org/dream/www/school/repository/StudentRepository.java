package org.dream.www.school.repository;

import org.springframework.data.repository.CrudRepository;

import org.dream.www.sys.po.Student;


public interface StudentRepository extends CrudRepository<Student, Integer> {

}
