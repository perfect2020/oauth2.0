package org.ezt.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by wangwr on 2016/4/6.
 */
@Entity
@Table(name = "ezt_user")
public class EztUser {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "eu_mobile")
    private String euMobile;

    @Column(name = "eu_number")
    private String euNumber;

    @Column(name = "eu_password")
    private String euPassword;

    @Column(name = "eu_nickName")
    private String euNickName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEuMobile() {
        return euMobile;
    }

    public void setEuMobile(String euMobile) {
        this.euMobile = euMobile;
    }

    public String getEuNumber() {
        return euNumber;
    }

    public void setEuNumber(String euNumber) {
        this.euNumber = euNumber;
    }

    public String getEuPassword() {
        return euPassword;
    }

    public void setEuPassword(String euPassword) {
        this.euPassword = euPassword;
    }

    public String getEuNickName() {
        return euNickName;
    }

    public void setEuNickName(String euNickName) {
        this.euNickName = euNickName;
    }
}
