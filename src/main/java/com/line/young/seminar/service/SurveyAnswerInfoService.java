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

/**
 * 
 * Survey answer information service 
 * 
 * @author jounghunyoung@gmail.com
 *
 */
@Service
public class SurveyAnswerInfoService {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    
    @Autowired
    private SurveyAnswerInfoRepository surveyAnswerInfoRepository;
    
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<SurveyAnswerInfo> findAnswerByEncryptId(String encryptId){
      return entityManager.createNamedQuery("SurveyAnswerInfo.findAnswerByEncryptId")
        .setParameter("encryptId", encryptId)
        .getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<SurveyAnswerInfo> findAllAnswerBySeminarId(String seminarId){
      return entityManager.createNamedQuery("SurveyAnswerInfo.findAllAnswerBySeminarId")
        .setParameter("seminarId", seminarId)
        .getResultList();
    }

    public List<SurveyAnswerInfo> findAllAnswerByIds(String encryptId, String seminarId){
        @SuppressWarnings("unchecked")
        List<SurveyAnswerInfo> surveyAnswerInfos = entityManager.createNamedQuery("SurveyAnswerInfo.findAllAnswerByIds")
        .setParameter("encryptId", encryptId)
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
        List<SurveyAnswerInfo> list = this.findAllAnswerByIds(surveyAnswerInfos.get(0).getEncrypt_id(), 
                surveyAnswerInfos.get(0).getSeminar_id());
        if (null != list) {
            for (SurveyAnswerInfo surveyAnswerInfo: list) {
                this.deleteOfSurveyAnswerInfo(surveyAnswerInfo);
            }
        }
        return surveyAnswerInfoRepository.saveAll(surveyAnswerInfos);
    }
    
    public List<Object> summary() {
        return surveyAnswerInfoRepository.summary();

    }

    public void deleteOfSurveyAnswerInfo(SurveyAnswerInfo surveyAnswerInfo) {
    	surveyAnswerInfoRepository.delete(surveyAnswerInfo);
    }
    
    public void deleteAllOfSurveyAnswerInfo(List<SurveyAnswerInfo> surveyAnswerInfos) {
    	surveyAnswerInfoRepository.deleteAll(surveyAnswerInfos);
    }

}
