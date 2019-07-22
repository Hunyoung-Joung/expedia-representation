/**
 * Copyright@ LINE 2019
 */
package com.line.young.seminar.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.line.young.seminar.entity.SurveyAnswerInfo;
import com.line.young.seminar.entity.SurveySum;

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
