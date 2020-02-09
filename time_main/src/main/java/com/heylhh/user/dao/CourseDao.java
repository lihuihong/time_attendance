package com.heylhh.user.dao;

import com.heylhh.user.pojo.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CourseDao extends JpaRepository<Course, String>, JpaSpecificationExecutor<Course> {
}
