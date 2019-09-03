package com.expedia.young.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="survey_answer_info")
@NamedQueries(value = {
    @NamedQuery(name = "SurveyAnswerInfo.findAnswerByEncryptId",
    query = " SELECT answer_info "
            + " FROM SurveyAnswerInfo answer_info "
            + "WHERE answer_info.encrypt_id = :encryptId "
            + "ORDER BY answer_info.survey_no DESC"),
    @NamedQuery(name = "SurveyAnswerInfo.findAllAnswerBySeminarId",
    query = " SELECT answer_infos "
            + " FROM SurveyAnswerInfo answer_infos "
            + "WHERE answer_infos.seminar_id = :seminarId "
            + "ORDER BY answer_infos.survey_no DESC"),
    @NamedQuery(name = "SurveyAnswerInfo.findAllAnswerByIds",
    query = " SELECT answer "
            + " FROM SurveyAnswerInfo answer "
            + "WHERE answer.encrypt_id = :encryptId "
            + "  AND answer.seminar_id = :seminarId "
            + "ORDER BY answer.survey_no DESC"),
    @NamedQuery(name = "SurveyAnswerInfo.summary",
    query = " SELECT answer.survey_no, " + 
    		"CASE " + 
    		"WHEN answer.survey_no = '1' THEN '勉強会内容について、理解できましたでしょうか。'  " + 
    		"WHEN answer.survey_no = '2' THEN '勉強会内容について、満足できましたでしょうか。'  " + 
    		"WHEN answer.survey_no = '3' THEN '勉強会の時間について、適切な長さでしたでしょうか。'  " + 
    		"WHEN answer.survey_no = '4' THEN '勉強会で参考になったアジェンダはございますか。（複数可）。'  " + 
    		"WHEN answer.survey_no = '5' THEN '後の勉強会で取り上げて欲しいテーマ等のご希望がございましたら、お聞かせ下さい。'  " + 
    		"WHEN answer.survey_no = '6' THEN 'その他にご意見、ご要望がございましたら、お聞かせ下さい。'  " + 
    		"END AS question, " + 
    		"answer.survey_answer, " + 
    		"CASE " + 
    		"WHEN answer.survey_answer = '1' THEN COUNT(answer.survey_answer) " + 
    		"WHEN answer.survey_answer = '2' THEN COUNT(answer.survey_answer) " + 
    		"WHEN answer.survey_answer = '3' THEN COUNT(answer.survey_answer) " + 
    		"WHEN answer.survey_answer = '4' THEN COUNT(answer.survey_answer) " + 
    		"WHEN answer.survey_answer = '5' THEN COUNT(answer.survey_answer) " + 
    		"END AS answer_count " + 
    		"FROM SurveyAnswerInfo answer " + 
    		"WHERE answer.survey_no in (1,2,3,4) " + 
    		"GROUP BY answer.survey_no, answer.survey_answer " + 
    		"ORDER BY answer.survey_no"),
    }
)
public class SurveyAnswerInfo {


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", nullable = false)
    private Long id;
    
    @Column(name="seminar_id", nullable = false)
    private String seminar_id;
    
    @Column(name="encrypt_id", nullable = false)
    private String encrypt_id;
    
    @Column(name="survey_no", nullable = false)
    private int survey_no;
    
    @Column(name="survey_answer", nullable = true)
    private String survey_answer;
    
    @CreationTimestamp
    @Column(name="create_at")
    private Date create_at;
    
    @UpdateTimestamp
    @Column(name="update_at")
    private Date update_at;

    public String getEncrypt_id() {
        return encrypt_id;
    }


    public void setEncrypt_id(String encrypt_id) {
        this.encrypt_id = encrypt_id;
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

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }
    
    @Override
    public String toString() {
        return "SurveyInfo [seminar_id="+seminar_id+", encrypt_id="+encrypt_id+", survey_no="+survey_no+", survey_answer="+survey_answer+"]";
    }
}
