/**
 * Copyright@ LINE 2019
 */
package com.expedia.young.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expedia.young.demo.entity.PersonalInfo;
import com.expedia.young.demo.repo.PersonalInfoRepository;

/**
 * 
 * User information service 
 * 
 * @author jounghunyoung@gmail.com
 *
 */
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

    public Optional<PersonalInfo> findByEncryptId(String encryptId) {
        return personalInfoRepository.findByEncryptId(encryptId);
    }
}
