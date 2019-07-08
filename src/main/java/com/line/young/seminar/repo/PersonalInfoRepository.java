/**
 * Copyright@ LINE 2019
 */
package com.line.young.seminar.repo;

import org.springframework.data.repository.CrudRepository;
import com.line.young.seminar.entity.PersonalInfo;

/**
 * 
 * @author jounghunyoung@gmail.com
 * 
 */
public interface PersonalInfoRepository extends CrudRepository<PersonalInfo, String> {}
