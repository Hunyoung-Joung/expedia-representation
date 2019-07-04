package com.line.young.seminar.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.line.young.seminar.entity.QuestionInfo;

public interface QuestionInfoRepository extends CrudRepository<QuestionInfo, String> {
    List<QuestionInfo> findAllUserQuestion(String userId);
}
