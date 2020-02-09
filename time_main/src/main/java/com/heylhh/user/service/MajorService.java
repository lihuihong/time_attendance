package com.heylhh.user.service;

import com.heylhh.common.util.IdWorker;
import com.heylhh.user.dao.MajorDao;
import com.heylhh.user.pojo.Major;
import com.heylhh.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MajorService {

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private MajorDao majorDao;

    /**
     * 获取全部专业信息
     * @return
     */
    public List<Major> findAll(){
        List<Major> majorList = majorDao.findAll();
        return majorList;
    }

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    public Major findById(String id) {
        return majorDao.findById(id).get();
    }

    /**
     * 条件查询+分页
     * @param major
     * @param page
     * @param size
     * @return
     */
    public Page<Major> findSearch(Major major,int page, int size){
        Specification<Major> specification = createSpecification(major);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return majorDao.findAll(specification,pageRequest);
    }

    /**
     * 增加专业信息
     * @param major
     * @return
     */
    public Major addMajor(Major major){
        major.setMajorId(idWorker.nextId() + "");
        Major save = majorDao.save(major);
        if (save != null){
            return save;
        }else {
            return null;
        }
    }

    /**
     * 更新专业信息
     * @param major
     * @return
     */
    public Major updateMajor(Major major){
        Major save = majorDao.save(major);
        if (save != null){
            return save;
        }else {
            return null;
        }
    }

    /**
     * 删除专业信息
     * @param id
     */
    public void deleteMajorById(String id){
        majorDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param major
     * @return
     */
    private Specification<Major> createSpecification(Major major) {

        return new Specification<Major>() {

            @Override
            public Predicate toPredicate(Root<Major> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (major.getMajorName()!= null && !"".equals(major.getMajorName())) {
                    predicateList.add(cb.like(root.get("majorName").as(String.class), "%" + major.getMajorName() + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };

    }
}
