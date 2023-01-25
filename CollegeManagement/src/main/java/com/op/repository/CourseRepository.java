package com.op.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.op.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
