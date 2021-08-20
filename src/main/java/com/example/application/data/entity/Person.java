package com.example.application.data.entity;

import com.example.application.data.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class Person extends AbstractEntity {

    private String surname;
    private String name;
    private String patronymic;
    private String phone;
    private String email;
    private String passportSeries;
    private String passportID;
    private String creditAmount;
    private String creditTerm;
    private String entryInterestRate;


    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPatronymic() {
        return patronymic;
    }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassportSeries() {
        return passportSeries;
    }
    public void setPassportSeries(String passportSeries) {
        this.passportSeries = passportSeries;
    }
    public String getPassportID() {
        return passportID;
    }
    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }
    public String getCreditAmount() {
        return creditAmount;
    }
    public void setCreditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
    }
    public String getCreditTerm() {
        return creditTerm;
    }
    public void setCreditTerm(String creditTerm) {
        this.creditTerm = creditTerm;
    }
    public String getEntryInterestRate() {
        return entryInterestRate;
    }
    public void setEntryInterestRate(String entryInterestRate) {
        this.entryInterestRate = entryInterestRate;
    }
}
