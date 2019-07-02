package com.line.young.seminar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.line.young.seminar.entity.QuestionInfo;
import com.line.young.seminar.repo.QuestionInfoRepository;

@Service
public class QuestionInfoService {
    
    @Autowired
    private QuestionInfoRepository questionInfoRepository;

    public Iterable<QuestionInfo> findAll() {
        return questionInfoRepository.findAll();
    }

    public Optional<QuestionInfo> findOne(String userId) {
        return questionInfoRepository.findById(userId);
    }

    public QuestionInfo save(QuestionInfo questionInfo) {
        return questionInfoRepository.save(questionInfo);
    }

    public void delete(String userId) {
        questionInfoRepository.deleteById(userId);
    }
    
    public void deleteAll() {
        questionInfoRepository.deleteAll();
    }
}
