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

    public Optional<PersonalInfo> findOne(String encryptId) {
        return personalInfoRepository.findById(encryptId);
    }

    public PersonalInfo save(PersonalInfo personalInfo) {
        return personalInfoRepository.save(personalInfo);
    }

    public void delete(String encryptId) {
        personalInfoRepository.deleteById(encryptId);
    }
    
    public void deleteAll() {
        personalInfoRepository.deleteAll();
    }
}
