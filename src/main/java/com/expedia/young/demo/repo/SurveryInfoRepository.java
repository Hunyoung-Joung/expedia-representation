/**
 * Copyright@ LINE 2019
 */
package com.expedia.young.demo.repo;


import org.springframework.data.repository.CrudRepository;

import com.expedia.young.demo.entity.SurveyInfo;

/**
 * 
 * @author jounghunyoung@gmail.com
 * 
 *
 */
public interface SurveryInfoRepository extends CrudRepository<SurveyInfo, String> {}
