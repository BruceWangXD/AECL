package com.aecl.demo.entity;

public class PersonInfo {
    private Integer personId;

    private String email;

    private String userName;

    private String password;

    private Integer academicYear;

    private Integer pcourse1;

    private Integer pcourse2;

    private Integer pcourse3;

    private Integer pcourse4;

    private Boolean theme;

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(Integer academicYear) {
        this.academicYear = academicYear;
    }

    public Integer getPcourse1() {
        return pcourse1;
    }

    public void setPcourse1(Integer pcourse1) {
        this.pcourse1 = pcourse1;
    }

    public Integer getPcourse2() {
        return pcourse2;
    }

    public void setPcourse2(Integer pcourse2) {
        this.pcourse2 = pcourse2;
    }

    public Integer getPcourse3() {
        return pcourse3;
    }

    public void setPcourse3(Integer pcourse3) {
        this.pcourse3 = pcourse3;
    }

    public Integer getPcourse4() {
        return pcourse4;
    }

    public void setPcourse4(Integer pcourse4) {
        this.pcourse4 = pcourse4;
    }

    public Boolean getTheme() {
        return theme;
    }

    public void setTheme(Boolean theme) {
        this.theme = theme;
    }


}