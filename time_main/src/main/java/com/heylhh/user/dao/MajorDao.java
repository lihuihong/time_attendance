package com.heylhh.user.dao;

import com.heylhh.user.pojo.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MajorDao extends JpaRepository<Major, String>, JpaSpecificationExecutor<Major> {
}
