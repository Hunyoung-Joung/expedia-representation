package com.line.young.seminar.repo;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.springframework.data.repository.CrudRepository;
import com.line.young.seminar.entity.QuestionInfo;

public interface QuestionInfoRepository extends CrudRepository<QuestionInfo, String> {
    
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public List<Account> findByname(String name){
//      return entityManager.createNamedQuery("Account.findByusername", Account.class)
//        .setParameter("name", name)
//        .getResultList();
//    }
//    List<QuestionInfo> findAllUserQuestion(String userId);
}
