/**
 * Copyright@ LINE 2019
 */
package com.expedia.young.demo.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.expedia.young.demo.entity.SurveyAnswerInfo;

/**
 * 
 * @author jounghunyoung@gmail.com
 * 
 *
 */
public interface SurveyAnswerInfoRepository extends CrudRepository<SurveyAnswerInfo, String> {

    SurveyAnswerInfo findAnswerByEncryptId(@Param("encryptId") String encryptId);
    
    List<SurveyAnswerInfo> findAllAnswerBySeminarId(@Param("seminarId") String seminarId);
    
    SurveyAnswerInfo findAllAnswerByIds(@Param("encryptId") String encryptId, @Param("seminarId") String seminarId);
    
    List<Object> summary();

}
