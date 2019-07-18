package com.line.young.seminar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.repository.Temporal;

@Entity
@Table(name="personal_info")
@NamedQuery(name = "PersonalInfo.findByEncryptId",
query = " SELECT info "
        + " FROM PersonalInfo info "
        + "WHERE info.encrypt_id = :encryptId ")
public class PersonalInfo {
    
    @Id
    @Column(name="user_id", nullable = false)
    private String user_id;
    
    @Column(name="encrypt_id", nullable = false)
    private String encrypt_id;
    
    @Column(name="display_name", nullable = true)
    private String display_name;
    
    @Column(name="user_name", nullable = true)
    private String user_name;
    
    @Column(name="company_name", nullable = true)
    private String company_name;
    
    @Column(name="job_type", nullable = true)
    private String job_type;
    
    @Column(name="is_confirmed", nullable = true)
    private boolean is_confirmed;
    

    @CreationTimestamp
    @Column(name="create_at")
    private Date create_at;
    
    @UpdateTimestamp
    @Column(name="update_at")
    private Date update_at;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    

    public String getEncrypt_id() {
        return encrypt_id;
    }

    public void setEncrypt_id(String encrypt_id) {
        this.encrypt_id = encrypt_id;
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
    

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    @Override
    public String toString() {
        return "PersonalInfo [user_id="+user_id+", encrypt_id="+encrypt_id+", display_name="+display_name+", user_name="+user_name+", company_name="
                +company_name+", job_type=" +job_type+", is_confirmed="+is_confirmed+"]";
    }
}
