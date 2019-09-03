/**
 * Copyright@ LINE 2019
 */

package com.line.young.seminar.service;

import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyInfoService {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    
//    @Autowired
//    private QuestionInfoRepository questionInfoRepository;
//    
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @SuppressWarnings("unchecked")
//    public List<QuestionInfo> findByUserId(String userId){
//      return entityManager.createNamedQuery("QuestionInfo.findAllUserQuestion")
//        .setParameter("userId", userId)
//        .getResultList();
//    }
//
//
//    public Iterable<QuestionInfo> findAllOfQuestionInfo() {
//        return questionInfoRepository.findAll();
//    }
//    
//    public List<QuestionInfo> findAllUserQuestion(String userId) {
//        logger.info("## QuestionInfoService findAllUserQuestion userId? "+userId);
//        return this.findByUserId(userId);
//    }
//
//    public QuestionInfo saveOfQuestionInfo(QuestionInfo questionInfo) {
//        return questionInfoRepository.save(questionInfo);
//    }
//
//    public void deleteOfQuestionInfo(String userId) {
//        questionInfoRepository.deleteById(userId);
//    }
//    
//    public void deleteAllOfQuestionInfo() {
//        questionInfoRepository.deleteAll();
//    }
}
