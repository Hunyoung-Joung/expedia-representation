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
import org.springframework.ui.Model;

import com.line.young.seminar.entity.SurveyAnswerInfo;
import com.line.young.seminar.repo.SurveyAnswerInfoRepository;
import static java.util.stream.IntStream.range;
import static java.util.stream.Collectors.toList;

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

    public List<SurveyAnswerInfo> findAllAnswerByIds(String userId, String seminarId){
        @SuppressWarnings("unchecked")
        List<SurveyAnswerInfo> surveyAnswerInfos = entityManager.createNamedQuery("SurveyAnswerInfo.findAllAnswerByIds")
        .setParameter("userId", userId)
        .setParameter("seminarId", seminarId)
        .getResultList();
        if (surveyAnswerInfos.isEmpty()) {
            return null;
        } else {
            return surveyAnswerInfos;
        }
    }

    public Iterable<SurveyAnswerInfo> findAllOfSurveyAnswerInfo() {
        return surveyAnswerInfoRepository.findAll();
    }
    
    public Iterable<SurveyAnswerInfo> saveOfSurveyAnswerInfos(List<SurveyAnswerInfo> surveyAnswerInfos) {
        return surveyAnswerInfoRepository.saveAll(surveyAnswerInfos);
    }
    
    
//    public Iterable<SurveyAnswerInfo> saveOfSurveyAnswerInfos(List<SurveyAnswerInfo> surveyAnswerInfos) {
//        for (SurveyAnswerInfo surveyAnswerInfo: surveyAnswerInfos) {
//            
////            surveyAnswerInfoRepository.save(surveyAnswerInfo);
//            
//            logger.info("##### saveOfSurveyAnswerInfos?"+surveyAnswerInfo.toString());
//        }
//        return surveyAnswerInfoRepository.saveAll(surveyAnswerInfos);
////        return surveyAnswerInfos;
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
