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
public interface SurveryInfoRepository extends CrudRepository<QuestionInfo, String> {

    List<QuestionInfo> findAllUserSurvey(@Param("userId") String userId);
}
