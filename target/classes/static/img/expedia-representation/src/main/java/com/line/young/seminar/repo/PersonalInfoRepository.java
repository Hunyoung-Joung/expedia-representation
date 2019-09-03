/**
 * Copyright@ LINE 
 * by jounghunyoung@gmail.com
 * 
 */
package com.line.young.seminar.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.line.young.seminar.entity.PersonalInfo;

/**
 * 
 * @author jounghunyoung@gmail.com
 * 
 */
public interface PersonalInfoRepository extends CrudRepository<PersonalInfo, String> {
    
    Optional<PersonalInfo> findByEncryptId(@Param("encryptId") String encryptId);
    
}
