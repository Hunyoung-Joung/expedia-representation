package com.line.young.seminar.repo;

import org.springframework.data.repository.CrudRepository;
import com.line.young.seminar.entity.PersonalInfo;

public interface QuestionInfoRepository extends CrudRepository<PersonalInfo, String> {}
