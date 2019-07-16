/**
 * Copyright@ LINE 2019
 */

package com.line.young.seminar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.line.young.seminar.entity.PersonalInfo;
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
    
    public SurveyAnswerInfo save(SurveyAnswerInfo surveyAnswerInfo) {
        return surveyAnswerInfoRepository.save(surveyAnswerInfo);
    }
    
    public Iterable<SurveyAnswerInfo> saveOfSurveyAnswerInfos(List<SurveyAnswerInfo> surveyAnswerInfos) {
    	for (SurveyAnswerInfo surveyAnswerInfo: this.findAllAnswerByIds(surveyAnswerInfos.get(0).getUser_id(), 
    	        surveyAnswerInfos.get(0).getSeminar_id())) {
    		
    		
//    		List<String> param = new ArrayList<String>();
//    		param.add(surveyAnswerInfo.getSeminar_id());
//    		param.add(surveyAnswerInfo.getUser_id());
//    		param.add(String.valueOf(surveyAnswerInfo.getSurvey_no()));
    		
//    		Iterable<SurveyAnswerInfo> temp = surveyAnswerInfoRepository.findAllById(param);
//    		if (null != temp) {
//    			surveyAnswerInfoRepository.save(surveyAnswerInfo);
//    		}
      
    		logger.info("##### saveOfSurveyAnswerInfos?"+surveyAnswerInfo.toString());
    		this.deleteOfSurveyAnswerInfo(surveyAnswerInfo);
    	}
//    	this.deleteAllOfSurveyAnswerInfo(surveyAnswerInfos);
        return surveyAnswerInfoRepository.saveAll(surveyAnswerInfos);
    	
//        return surveyAnswerInfos;

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
    public void deleteOfSurveyAnswerInfo(SurveyAnswerInfo surveyAnswerInfo) {
    	surveyAnswerInfoRepository.delete(surveyAnswerInfo);
    }
    
    public void deleteAllOfSurveyAnswerInfo(List<SurveyAnswerInfo> surveyAnswerInfos) {
    	logger.info("##### deleteAllOfSurveyAnswerInfo?"+surveyAnswerInfos.size());
    	surveyAnswerInfoRepository.deleteAll(surveyAnswerInfos);
    }
//    
//    public void deleteAllOfQuestionInfo() {
//        questionInfoRepository.deleteAll();
//    }
}
