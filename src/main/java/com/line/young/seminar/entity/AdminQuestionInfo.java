package com.line.young.seminar.entity;

import javax.persistence.Entity;

import org.springframework.data.annotation.Id;


@Entity
public class AdminQuestionInfo {

	@Id
    private PersonalInfo PersonalInfo;
    
    private QuestionInfo QuestionInfo;

	public PersonalInfo getPersonalInfo() {
		return PersonalInfo;
	}

	public void setPersonalInfo(PersonalInfo personalInfo) {
		PersonalInfo = personalInfo;
	}

	public QuestionInfo getQuestionInfo() {
		return QuestionInfo;
	}

	public void setQuestionInfo(QuestionInfo questionInfo) {
		QuestionInfo = questionInfo;
	}



}
