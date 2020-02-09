package com.heylhh.user.service;

import com.heylhh.common.util.IdWorker;
import com.heylhh.user.dao.QuestionsDao;
import com.heylhh.user.pojo.Questions;
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
public class QuestionsService {

    @Autowired
    private IdWorker idWorker;
    @Autowired
    private QuestionsDao questionsDao;

    public List<Questions> findAll(){
        List<Questions> QuestionsList = questionsDao.findAll();
        return QuestionsList;
    }

    public Page<Questions> findSearch(Questions questions,int page,int size){
        Specification<Questions> specification = createSpecification(questions);
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return questionsDao.findAll(specification, pageRequest);
    }

    public List<Questions> findSearch(Questions questions){
        Specification<Questions> specification = createSpecification(questions);
        return questionsDao.findAll(specification);
    }

    public Questions addQuestions(Questions questions){
        questions.setQuestionsId(idWorker.nextId()+"");
        Questions save = questionsDao.save(questions);
        if (save !=null){
            return save;
        }else{
            return null;
        }
    }

    public Questions updateQuestions(Questions questions){
        Questions save = questionsDao.save(questions);
        if (save != null){
            return save;
        }else {
            return null;
        }
    }

    public Questions findById(String id){
        return questionsDao.findById(id).get();
    }

    public void deleteQuestionsById(String id){
        questionsDao.deleteById(id);
    }


    /**
     * 动态条件构建
     *
     * @param questions
     * @return
     */
    private Specification<Questions> createSpecification(Questions questions) {

        return new Specification<Questions>() {

            @Override
            public Predicate toPredicate(Root<Questions> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (questions.getQuestionsTitle()!= null && !"".equals(questions.getQuestionsTitle())) {
                    predicateList.add(cb.like(root.get("questionsTitle").as(String.class), "%" + questions.getQuestionsTitle() + "%"));
                }
                if (questions.getCourseId()!= null && !"".equals(questions.getCourseId())) {
                    predicateList.add(cb.like(root.get("courseId").as(String.class), "%" + questions.getCourseId() + "%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };

    }



}
