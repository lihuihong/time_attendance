package com.heylhh.user.service;

import com.heylhh.common.util.IdWorker;
import com.heylhh.user.dao.UserDao;
import com.heylhh.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 根据登陆名和密码查询
     * @param loginName
     * @param password
     * @return
     */
    public User findByLoginNameAndPassword(String loginName, String password,String userType) {
        User admin = userDao.findByUserNameAndUserType(loginName,userType);
        if (admin != null && bCryptPasswordEncoder.matches(password, admin.getUserPassword())) {
            return admin;
        } else {
            return null;
        }
    }

    /**
     * 查询全部列表
     *
     * @return
     */
    public List<User> findAll() {
        List<User> articleList = userDao.findAll();
        return articleList;
    }

    /**
     * 条件查询+分页
     *
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<User> findSearch(Map whereMap, int page, int size) {
        Specification<User> specification = createSpecification(whereMap);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return userDao.findAll(specification, pageRequest);
    }

    /**
     * 条件查询
     *
     * @param whereMap
     * @return
     */
    public List<User> findSearch(Map whereMap) {
        Specification<User> specification = createSpecification(whereMap);
        return userDao.findAll(specification);
    }
    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public User findById(String id) {
        return userDao.findById(id).get();
    }

    /**
     * 找回密码
     * @param user
     * @param newPassword
     * @return
     */
    public User retrieve(User user,String newPassword) {
        User admin = userDao.findByUserNameAndUserType(user.getUserName(),user.getUserType());
        if (admin != null && bCryptPasswordEncoder.matches(user.getUserPassword(), admin.getUserPassword())) {
            String newPasswordInfo = bCryptPasswordEncoder.encode(newPassword);//加密后的密码
            admin.setUserPassword(newPasswordInfo);
            update(admin);
            return admin;
        } else {
            return null;
        }
    }

    public User findByUserNameAndUserType(User user){
        User admin = userDao.findByUserNameAndUserType(user.getUserName(),user.getUserType());
        if (admin != null){
            return admin;
        }else {
            return null;
        }
    }

    /**
     * 增加
     *
     * @param user
     */
    public User add(User user) {
        User userInfo = userDao.findByUserNameAndUserType(user.getUserName(),user.getUserType());
        if (userInfo != null){
            return userInfo;
        }else {
            user.setUserId(idWorker.nextId() + "");
            String newPassword = bCryptPasswordEncoder.encode(user.getUserPassword());//加密后的密码
            user.setUserPassword(newPassword);
            userDao.save(user);
            return null;
        }

    }

    /**
     * 修改
     *
     * @param user
     */
    public User update(User user) {
        User save = userDao.save(user);
        if (save!=null){
            return save;
        }else {
            return null;
        }
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(String id) {
        userDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<User> createSpecification(Map searchMap) {

        return new Specification<User>() {

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("userId") != null && !"".equals(searchMap.get("userId"))) {
                    predicateList.add(cb.like(root.get("userId").as(String.class), "%" + (String) searchMap.get("userId") + "%"));
                }
                // 用户类型
                if (searchMap.get("userType") != null && !"".equals(searchMap.get("userType"))) {
                    predicateList.add(cb.like(root.get("userType").as(String.class), "%" + (String) searchMap.get("userType") + "%"));
                }
                // 学号
                if (searchMap.get("userCode") != null && !"".equals(searchMap.get("userCode"))) {
                    predicateList.add(cb.like(root.get("userCode").as(String.class), "%" + (String) searchMap.get("userCode") + "%"));
                }
                // 姓名
                if (searchMap.get("userName") != null && !"".equals(searchMap.get("userName"))) {
                    predicateList.add(cb.like(root.get("userName").as(String.class), "%" + (String) searchMap.get("userName") + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));

            }
        };

    }

}
