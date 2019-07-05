package com.line.young.seminar.service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import com.line.young.seminar.entity.QuestionInfo;
import com.line.young.seminar.repo.QuestionInfoRepository;

@Service
public class QuestionInfoService {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    
    @Autowired
    private QuestionInfoRepository questionInfoRepository;
    
    @PersistenceContext
    private EntityManager entityManager;

    public List<QuestionInfo> findByUserId(String userId){
      return entityManager.createNamedQuery("QuestionInfo.findAllUserQuestion")
        .setParameter("userId", userId)
        .getResultList();
    }


    public Iterable<QuestionInfo> findAllOfQuestionInfo() {
        return questionInfoRepository.findAll();
    }

//    public Optional<QuestionInfo> findOneOfQuestionInfo(String userId) {
//        
//        return questionInfoRepository.fifindById(userId);
//    }
    
    public List<QuestionInfo> findAllUserQuestion(String userId) {
        logger.info("## QuestionInfoService findAllUserQuestion userId? "+userId);
        return this.findByUserId(userId);
    }

    public QuestionInfo saveOfQuestionInfo(QuestionInfo questionInfo) {
        return questionInfoRepository.save(questionInfo);
    }

    public void deleteOfQuestionInfo(String userId) {
        questionInfoRepository.deleteById(userId);
    }
    
    public void deleteAllOfQuestionInfo() {
        questionInfoRepository.deleteAll();
    }
}

//@Repository("QuestionInfoRepositoryImpl")
//class QuestionInfoRepositoryImpl {
//
//  @PersistenceContext
//  private EntityManager entityManager;
//
//  public List<QuestionInfo> findByUserId(String userId){
//    return entityManager.createNamedQuery("QuestionInfo.findAllUserQuestion")
//      .setParameter("userId", userId)
//      .getResultList();
//  }
//}
