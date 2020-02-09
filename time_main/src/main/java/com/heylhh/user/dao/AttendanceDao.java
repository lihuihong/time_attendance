package com.heylhh.user.dao;

import com.heylhh.user.pojo.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AttendanceDao extends JpaRepository<Attendance, String>, JpaSpecificationExecutor<Attendance> {
}
