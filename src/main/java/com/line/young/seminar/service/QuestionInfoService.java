/**
 * Copyright@ LINE 2019
 */

package com.line.young.seminar.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.line.young.seminar.entity.QuestionInfo;
import com.line.young.seminar.repo.QuestionInfoRepository;

/**
 * 
 * Question information service 
 * 
 * @author jounghunyoung@gmail.com
 *
 */
@Service
public class QuestionInfoService {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    
    @Autowired
    private QuestionInfoRepository questionInfoRepository;
    
    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<QuestionInfo> findByEncryptId(String encryptId){
      return entityManager.createNamedQuery("QuestionInfo.findAllrQuestionByEncryptId")
        .setParameter("encryptId", encryptId)
        .getResultList();
    }

    public Iterable<QuestionInfo> findAllOfQuestionInfo() {
        return questionInfoRepository.findAll();
    }

    public Optional<QuestionInfo> findById(Long qNo) {
        return questionInfoRepository.findById(qNo);
    }
    public QuestionInfo saveOfQuestionInfo(QuestionInfo questionInfo) {
        return questionInfoRepository.save(questionInfo);
    }
    
    public void deleteById(Long qNo) {
        questionInfoRepository.deleteById(qNo);
    }
    
    public void deleteAllOfQuestionInfo() {
        questionInfoRepository.deleteAll();
    }

}
