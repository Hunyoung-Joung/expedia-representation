package com.line.young.seminar.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="personal_info")
//@Table(schema = "seminar-db", name = "personal_info")
public class PersonalInfo {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id", nullable = false)
    private String user_id;
    
    @Column(name="display_name", nullable = true)
    private String display_name;
    
    @Column(name="user_name", nullable = true)
    private String user_name;
    
    @Column(name="company_name", nullable = true)
    private String company_name;
    
    @Column(name="job_type", nullable = false)
    private String job_type;
    
    @Column(name="is_confirmed", nullable = true)
    private boolean is_confirmed;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getJob_type() {
        return job_type;
    }

    public void setJob_type(String job_type) {
        this.job_type = job_type;
    }

    public boolean isIs_confirmed() {
        return is_confirmed;
    }

    public void setIs_confirmed(boolean is_confirmed) {
        this.is_confirmed = is_confirmed;
    }


}
