package com.expedia.young.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserInfo {

    @Id
    @Column(name="id", nullable = false)
    private String id;
    
    @Column(name="password", nullable = true)
    private String password;
    
       
    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "PersonalInfo [id="+id+", password="+password+"]";
    }
}
