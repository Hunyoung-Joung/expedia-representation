/**
 * Copyright@ LINE 2019
 */
package com.line.young.seminar.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.line.young.seminar.entity.QuestionInfo;

/**
 * 
 * @author jounghunyoung@gmail.com
 * 
 *
 */
public interface QuestionInfoRepository extends CrudRepository<QuestionInfo, String> {

    List<QuestionInfo> findAllrQuestionByEncryptId(@Param("encryptId") String encryptId); // need to @named query name SurveyAnswerInfo.findAllrQuestionByEncryptId"
}
