package com.line.young.seminar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.line.young.seminar.entity.PersonalInfo;
import com.line.young.seminar.repo.QuestionInfoRepository;

@Service
public class QuestionInfoService {
    
    @Autowired
    private QuestionInfoRepository questionInfoRepository;

    public Iterable<PersonalInfo> findAll() {
        return questionInfoRepository.findAll();
    }

    public Optional<PersonalInfo> findOne(String userId) {
        return questionInfoRepository.findById(userId);
    }

    public PersonalInfo save(PersonalInfo personalInfo) {
        return questionInfoRepository.save(personalInfo);
    }

    public void delete(String userId) {
        questionInfoRepository.deleteById(userId);
    }
    
    public void deleteAll() {
        questionInfoRepository.deleteAll();
    }
}
