package com.line.young.seminar.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class SurveyAnswerInfos implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    private List<SurveyAnswerInfo> surveyAnswerInfo;

	public List<SurveyAnswerInfo> getSurveyAnswerInfo() {
		return surveyAnswerInfo;
	}

	public void setSurveyAnswerInfo(List<SurveyAnswerInfo> surveyAnswerInfo) {
		this.surveyAnswerInfo = surveyAnswerInfo;
	}
    
    
}
