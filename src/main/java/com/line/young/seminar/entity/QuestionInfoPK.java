package com.line.young.seminar.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

public class QuestionInfoPK implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Long q_no;
    
    private String user_id;

    private String seminar_id;
    

    
    public Long getQ_no() {
        return q_no;
    }


    public void setQ_no(Long q_no) {
        this.q_no = q_no;
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
}
