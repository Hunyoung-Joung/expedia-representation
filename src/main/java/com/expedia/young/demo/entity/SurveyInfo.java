package com.expedia.young.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="survey_info")
//@NamedQuery(name = "QuestionInfo.findAllUserQuestion",
//query = "SELECT info "
//        + "FROM QuestionInfo info "
//        + "WHERE info.user_id = :userId "
//        + "ORDER BY info.q_no DESC")
public class SurveyInfo  {

    @Id
    @Column(name="seminar_id", nullable = false)
    private Long seminar_id;

    @Column(name="survey_no", nullable = false)
    private String survey_no;
    
    @Column(name="survey_contents", nullable = false)
    private String survey_contents;
    

    public Long getSeminar_id() {
        return seminar_id;
    }


    public void setSeminar_id(Long seminar_id) {
        this.seminar_id = seminar_id;
    }


    public String getSurvey_no() {
        return survey_no;
    }


    public void setSurvey_no(String survey_no) {
        this.survey_no = survey_no;
    }


    public String getSurvey_contents() {
        return survey_contents;
    }


    public void setSurvey_contents(String survey_contents) {
        this.survey_contents = survey_contents;
    }


    @Override
    public String toString() {
        return "SurveyInfo [seminar_id="+seminar_id+", survey_no="+survey_no+", seminar_id="
                +seminar_id+", survey_contents="+survey_contents+"]";
    }
}
