package com.heylhh.user.dao;

import com.heylhh.user.pojo.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface QuestionsDao extends JpaRepository<Questions, String>, JpaSpecificationExecutor<Questions> {
}
