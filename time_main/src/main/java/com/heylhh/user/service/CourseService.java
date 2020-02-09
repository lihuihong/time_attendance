package com.heylhh.user.service;

import com.heylhh.common.util.IdWorker;
import com.heylhh.user.dao.CourseDao;
import com.heylhh.user.pojo.Course;
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
public class CourseService {

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private CourseDao courseDao;

    public List<Course> findAll(){
        List<Course> courseList = courseDao.findAll();
        return courseList;
    }

    public Page<Course> findSearch(Course course,int page,int size){
        Specification<Course> specification = createSpecification(course);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return courseDao.findAll(specification, pageRequest);
    }

    public Course addCourse(Course course){
        course.setCourseId(idWorker.nextId()+"");
        Course save = courseDao.save(course);
        if (save !=null){
            return save;
        }else{
            return null;
        }
    }

    public Course updateCourse(Course course){
        Course save = courseDao.save(course);
        if (save != null){
            return save;
        }else {
            return null;
        }
    }

    public Course findById(String id){
        return courseDao.findById(id).get();
    }

    public void deleteCourseById(String id){
        courseDao.deleteById(id);
    }


    /**
     * 动态条件构建
     *
     * @param course
     * @return
     */
    private Specification<Course> createSpecification(Course course) {

        return new Specification<Course>() {

            @Override
            public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (course.getCourseName()!= null && !"".equals(course.getCourseName())) {
                    predicateList.add(cb.like(root.get("courseName").as(String.class), "%" + course.getCourseName() + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };

    }



}
