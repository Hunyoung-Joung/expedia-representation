/**
 * Copyright@ LINE 
 * by jounghunyoung@gmail.com
 * 
 */
package com.expedia.young.demo.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.expedia.young.demo.entity.PersonalInfo;

/**
 * 
 * @author jounghunyoung@gmail.com
 * 
 */
public interface PersonalInfoRepository extends CrudRepository<PersonalInfo, String> {
    
    Optional<PersonalInfo> findByEncryptId(@Param("encryptId") String encryptId);
    
}
