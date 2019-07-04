package com.line.young.seminar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="question_info")
//@IdClass(QuestionInfoPK.class)
public class QuestionInfo {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="q_no", nullable = false)
    private Long q_no;
    
//    @Id
    @Column(name="user_id", nullable = false)
    private String user_id;
    
//    @Id
    @Column(name="seminar_id", nullable = false)
    private String seminar_id;
    
    @Column(name="q_category", nullable = false)
    private String q_category;
    
    @Column(name="q_contents", nullable = false)
    private String q_contents;
    
    @Column(name="is_selected", nullable = false)
    private boolean is_selected;

    
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


    public String getQ_category() {
        return q_category;
    }


    public void setQ_category(String q_category) {
        this.q_category = q_category;
    }


    public String getQ_contents() {
        return q_contents;
    }


    public void setQ_contents(String q_contents) {
        this.q_contents = q_contents;
    }


    public boolean isIs_selected() {
        return is_selected;
    }


    public void setIs_selected(boolean is_selected) {
        this.is_selected = is_selected;
    }


    @Override
    public String toString() {
        return "PersonalInfo [q_no="+q_no+", user_id="+user_id+", seminar_id="+seminar_id+", q_category="
                +q_category+", q_contents=" +q_contents+", is_selected="+is_selected+"]";
    }
}
