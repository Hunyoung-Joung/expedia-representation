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

import com.line.young.seminar.entity.SurveyAnswerInfo;
import com.line.young.seminar.repo.SurveyAnswerInfoRepository;

@Service
public class SurveyAnswerInfoService {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    
    @Autowired
    private SurveyAnswerInfoRepository surveyAnswerInfoRepository;
    
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<SurveyAnswerInfo> findAnswerByUserId(String userId){
      return entityManager.createNamedQuery("SurveyAnswerInfo.findAnswerByUserId")
        .setParameter("userId", userId)
        .getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<SurveyAnswerInfo> findAllAnswerBySeminarId(String seminarId){
      return entityManager.createNamedQuery("SurveyAnswerInfo.findAllAnswerBySeminarId")
        .setParameter("seminarId", seminarId)
        .getResultList();
    }

    public SurveyAnswerInfo findAllAnswerByIds(String userId, String seminarId){
        Object obj = entityManager.createNamedQuery("SurveyAnswerInfo.findAllAnswerByIds")
        .setParameter("userId", userId)
        .setParameter("seminarId", seminarId)
        .getSingleResult();
        if (null ==  obj) {
            return new SurveyAnswerInfo();
        } else {
            return (SurveyAnswerInfo)obj;
        }
    }

    public Iterable<SurveyAnswerInfo> findAllOfSurveyAnswerInfo() {
        return surveyAnswerInfoRepository.findAll();
    }
    
//    public List<SurveyAnswerInfo> findAnswerByUserId(String userId) {
//        logger.info("## SurveyAnswerInfoService findAnswerByUserId userId? "+userId);
//        return this.findByUserId(userId);
//    }
//    
//    public List<SurveyAnswerInfo> findAllAnswerBySeminarId(String seminarId) {
//        logger.info("## SurveyAnswerInfoService findAllAnswerBySeminarId seminarId? "+seminarId);
//        return this.findBySeminarId(seminarId);
//    }
//    public SurveyAnswerInfo findAllAnswerByIds(String userId, String seminarId) {
//        logger.info("## SurveyAnswerInfoService findAllAnswerByIds userId? "+userId+", seminarId? "+seminarId);
//        return this.findByIds(userId, seminarId);
//    }
    
    
    public SurveyAnswerInfo saveOfSurveyAnswerInfo(SurveyAnswerInfo surveyAnswerInfo) {
        return surveyAnswerInfoRepository.save(surveyAnswerInfo);
    }
//
//    public void deleteOfQuestionInfo(String userId) {
//        questionInfoRepository.deleteById(userId);
//    }
//    
//    public void deleteAllOfQuestionInfo() {
//        questionInfoRepository.deleteAll();
//    }
}
