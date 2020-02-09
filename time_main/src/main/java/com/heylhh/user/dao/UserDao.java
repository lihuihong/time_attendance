package com.heylhh.user.dao;

import com.heylhh.user.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    public User findByUserNameAndUserType(String userName,String userType);

    public List<User> findAllByUserType(String userType);
}
