/**
 * Copyright@ LINE 2019
 */
package com.line.young.seminar.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.line.young.seminar.entity.QuestionInfo;
import com.line.young.seminar.entity.SurveyInfo;

/**
 * 
 * @author jounghunyoung@gmail.com
 * 
 *
 */
public interface SurveryInfoRepository extends CrudRepository<SurveyInfo, String> {

//    List<QuestionInfo> findAllUserSurvey(@Param("userId") String userId);
}
