package com.line.young.seminar.repo;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.line.young.seminar.entity.PersonalInfo;

public interface PersonalInfoRepository extends CrudRepository<PersonalInfo, String> {}
