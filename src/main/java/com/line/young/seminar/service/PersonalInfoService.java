package com.line.young.seminar.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.line.young.seminar.entity.PersonalInfo;
import com.line.young.seminar.repo.PersonalInfoRepository;

@Service
public class PersonalInfoService {
    
    @Autowired
    private PersonalInfoRepository personalInfoRepository;

    public Iterable<PersonalInfo> findAll() {
        return personalInfoRepository.findAll();
    }

    public Optional<PersonalInfo> findOne(String userId) {
        return personalInfoRepository.findById(userId);
    }

    public PersonalInfo save(PersonalInfo personalInfo) {
        return personalInfoRepository.save(personalInfo);
    }

    public void delete(String userId) {
        personalInfoRepository.deleteById(userId);
    }
    
    public void deleteAll() {
        personalInfoRepository.deleteAll();
    }
}
