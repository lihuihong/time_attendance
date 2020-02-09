package com.heylhh.user.service;


import com.heylhh.common.util.IdWorker;
import com.heylhh.user.dao.AttendanceDao;
import com.heylhh.user.pojo.Attendance;
import com.heylhh.user.util.UserInfoUtil;
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
public class AttendanceService {

    @Autowired
    private AttendanceDao attendanceDao;

    @Autowired
    private IdWorker idWorker;

    public List<Attendance> findAll(){
        List<Attendance> attendanceList = attendanceDao.findAll();
        return attendanceList;
    }

    public Page<Attendance> findSearch(Attendance attendance, int page, int size){
        Specification<Attendance> specification = createSpecification(attendance);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return attendanceDao.findAll(specification, pageRequest);
    }

    public List<Attendance> findSearch(Attendance attendance){
        Specification<Attendance> specification = createSpecification(attendance);
        return attendanceDao.findAll(specification);
    }

    public Attendance addAttendance(Attendance attendance){
        attendance.setAttendanceId(idWorker.nextId()+"");
        Attendance save = attendanceDao.save(attendance);
        if (save !=null){
            return save;
        }else{
            return null;
        }
    }

    public Attendance updateAttendance(Attendance attendance){
        Attendance save = attendanceDao.save(attendance);
        if (save != null){
            return save;
        }else {
            return null;
        }
    }

    public void deleteAttendanceById(String id){
        attendanceDao.deleteById(id);
    }


    /**
     * 动态条件构建
     *
     * @param attendance
     * @return
     */
    private Specification<Attendance> createSpecification(Attendance attendance) {

        return new Specification<Attendance>() {

            @Override
            public Predicate toPredicate(Root<Attendance> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (attendance.getUserId()!= null && !"".equals(attendance.getUserId())) {
                    predicateList.add(cb.like(root.get("userId").as(String.class), "%" + attendance.getUserId() + "%"));
                }
                if (attendance.getCourseId()!= null && !"".equals(attendance.getCourseId())) {
                    predicateList.add(cb.like(root.get("courseId").as(String.class), "%" + attendance.getCourseId() + "%"));
                }
                if (attendance.getCourseName()!= null && !"".equals(attendance.getCourseName())) {
                    predicateList.add(cb.like(root.get("courseName").as(String.class), "%" + attendance.getCourseName() + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };

    }

}
