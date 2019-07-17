package com.line.young.seminar.entity;

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
    
    @Column(name="encrypt_id", nullable = true)
    private String encrypt_id;
    
    @Column(name="user_id", nullable = true)
    private String user_id;
    
       
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


    public String getEncrypt_id() {
        return encrypt_id;
    }


    public void setEncrypt_id(String encrypt_id) {
        this.encrypt_id = encrypt_id;
    }


    public String getUser_id() {
        return user_id;
    }


    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }


    @Override
    public String toString() {
        return "PersonalInfo [id="+id+", password="+password+", encrypt_id="+encrypt_id+", user_id="
                +user_id+"]";
    }
}
