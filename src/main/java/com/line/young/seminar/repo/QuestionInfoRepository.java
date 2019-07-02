package com.line.young.seminar.repo;

import org.springframework.data.repository.CrudRepository;
import com.line.young.seminar.entity.PersonalInfo;
import com.line.young.seminar.entity.QuestionInfo;

public interface QuestionInfoRepository extends CrudRepository<QuestionInfo, String> {}
