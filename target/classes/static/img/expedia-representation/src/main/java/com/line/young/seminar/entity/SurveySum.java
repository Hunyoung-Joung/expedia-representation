package com.line.young.seminar.entity;

import javax.persistence.Column;

public class SurveySum  {

	@Column(name="survey_no")
    private String survey_no;
	@Column(name="question")
    private String question;
	@Column(name="survey_answer")
    private String survey_answer;
	@Column(name="answer_count")
    private String answer_count;

	public String getSurvey_no() {
		return survey_no;
	}

	public void setSurvey_no(String survey_no) {
		this.survey_no = survey_no;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getSurvey_answer() {
		return survey_answer;
	}

	public void setSurvey_answer(String survey_answer) {
		this.survey_answer = survey_answer;
	}

	public String getAnswer_count() {
		return answer_count;
	}

	public void setAnswer_count(String answer_count) {
		this.answer_count = answer_count;
	}
    
}
