package com.line.young.seminar.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.line.young.seminar.entity.QuestionInfo;

public interface QuestionInfoRepository extends CrudRepository<QuestionInfo, String> {
    
//    @PersistenceContext
//    private final EntityManager entityManager;
//
//    @SuppressWarnings("unchecked")
//    public default List<QuestionInfo> findAllUserQuestion(String userId){
//      return entityManager.createNamedQuery("QuestionInfo.findAllUserQuestion")
//        .setParameter("userId", userId)
//        .getResultList();
//    }
    List<QuestionInfo> findAllUserQuestion(@Param("userId") String userId);
}
