/**
 * Copyright@ LINE 2019
 */
package com.line.young.seminar.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.line.young.seminar.entity.SurveyAnswerInfo;

/**
 * 
 * @author jounghunyoung@gmail.com
 * 
 *
 */
public interface SurveyAnswerInfoRepository extends CrudRepository<SurveyAnswerInfo, String> {

    SurveyAnswerInfo findAnswerByUserId(@Param("userId") String user_id);
    
    List<SurveyAnswerInfo> findAllAnswerBySeminarId(@Param("seminarId") String seminar_id);
}
