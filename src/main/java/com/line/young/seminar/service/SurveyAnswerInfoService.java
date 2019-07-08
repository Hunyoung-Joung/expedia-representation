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
    public SurveyAnswerInfo findByUserId(String userId){
      return (SurveyAnswerInfo) entityManager.createNamedQuery("SurveyAnswerInfo.findByUserId")
        .setParameter("userId", userId)
        .getSingleResult();
    }
    
    @SuppressWarnings("unchecked")
    public List<SurveyAnswerInfo> findBySeminarId(String seminarId){
      return entityManager.createNamedQuery("SurveyAnswerInfo.findBySeminarId")
        .setParameter("seminar_id", seminarId)
        .getResultList();
    }


    public Iterable<SurveyAnswerInfo> findAllOfSurveyAnswerInfo() {
        return surveyAnswerInfoRepository.findAll();
    }
    
    public SurveyAnswerInfo findAnswerByUserId(String userId) {
        logger.info("## SurveyAnswerInfoService findAnswerByUserId userId? "+userId);
        return this.findByUserId(userId);
    }
    
    public List<SurveyAnswerInfo> findAllAnswerBySeminarId(String seminarId) {
        logger.info("## SurveyAnswerInfoService findAllAnswerBySeminarId seminarId? "+seminarId);
        return this.findBySeminarId(seminarId);
    }

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
