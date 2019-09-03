/**
 * Copyright@ LINE 2019
 */
package com.expedia.young.demo.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.expedia.young.demo.entity.QuestionInfo;

/**
 * 
 * @author jounghunyoung@gmail.com
 * 
 *
 */
public interface QuestionInfoRepository extends CrudRepository<QuestionInfo, Object> {
    // need to @named query name SurveyAnswerInfo.findAllrQuestionByEncryptId"
    List<QuestionInfo> findAllrQuestionByEncryptId(@Param("encryptId") String encryptId); 
}
