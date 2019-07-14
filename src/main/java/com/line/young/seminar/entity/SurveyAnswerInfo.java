package com.line.young.seminar.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="survey_answer_info")
@NamedQueries(value = {
    @NamedQuery(name = "SurveyAnswerInfo.findAnswerByUserId",
    query = " SELECT answer_info "
            + " FROM SurveyAnswerInfo answer_info "
            + "WHERE answer_info.user_id = :userId "
            + "ORDER BY answer_info.survey_no DESC"),
    @NamedQuery(name = "SurveyAnswerInfo.findAllAnswerBySeminarId",
    query = " SELECT answer_infos "
            + " FROM SurveyAnswerInfo answer_infos "
            + "WHERE answer_infos.seminar_id = :seminarId "
            + "ORDER BY answer_infos.survey_no DESC"),
    @NamedQuery(name = "SurveyAnswerInfo.findAllAnswerByIds",
    query = " SELECT answer "
            + " FROM SurveyAnswerInfo answer "
            + "WHERE answer.user_id = :userId "
            + "  AND answer.seminar_id = :seminarId "
            + "ORDER BY answer.survey_no DESC"),
    }
)
public class SurveyAnswerInfo {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private Long id;
    
    @Column(name="seminar_id", nullable = false)
    private String seminar_id;
    
    @Column(name="user_id", nullable = false)
    private String user_id;
    
    @Column(name="survey_no", nullable = false)
    private int survey_no;
    
    @Column(name="survey_answer", nullable = true)
    private String survey_answer;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUser_id() {
        return user_id;
    }


    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


    public String getSeminar_id() {
        return seminar_id;
    }


    public void setSeminar_id(String seminar_id) {
        this.seminar_id = seminar_id;
    }


    public int getSurvey_no() {
        return survey_no;
    }


    public void setSurvey_no(int survey_no) {
        this.survey_no = survey_no;
    }


    public String getSurvey_answer() {
        return survey_answer;
    }


    public void setSurvey_answer(String survey_answer) {
        this.survey_answer = survey_answer;
    }


    @Override
    public String toString() {
        return "SurveyInfo [seminar_id="+seminar_id+", user_id="+user_id+", survey_no="+survey_no+", survey_answer="+survey_answer+"]";
    }
}
