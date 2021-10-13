package com.example.application.model;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;


/**
 * Модель данных для взаимодействия приложение с базой данных.
 */
@Entity
@Table(name = "CLIENTS")


public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CLIENTID", updatable = false, nullable = false, unique=true)
    private UUID CLIENTID;


    @Column(name = "SURNAME")
    private String SURNAME;

    @Column(name = "NAME")
    private String NAME;

    @Column(name = "PATRONYMIC")
    private String PATRONYMIC;

    @Column(name = "PHONE")
    private String PHONE;

    @Column(name = "PASSPORTSERIES")
    private int PASSPORTSERIES;

    @Column(name = "PASSPORTID")
    private int PASSPORTID;

    @Column(name = "credit_amount")
    private Double creditAmount;

    @Column(name = "credit_term")
    private Double creditTerm;

    @Column(name = "entry_interest_rate")
    private Double entryInterestRate;

    @Column(name = "email")
    private String email;


    @OneToMany(mappedBy = "clients", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentSchedule> paymentSchedules;

    public void setCLIENTID(UUID CLIENTID) {
        this.CLIENTID = CLIENTID;
    }

    public UUID getCLIENTID() {
        return CLIENTID;
    }


    public List<PaymentSchedule> getPaymentSchedules() {
        return paymentSchedules;
    }

    public void setPaymentSchedules(List<PaymentSchedule> paymentSchedules) {
        this.paymentSchedules = paymentSchedules;
    }

    public Clients(UUID CLIENTID, String SURNAME, String NAME, String PATRONYMIC, String PHONE, String email, int PASSPORTSERIES, int PASSPORTID, double creditAmount, double creditTerm, double entryInterestRate ) {
        this.CLIENTID = CLIENTID;
        this.SURNAME = SURNAME;
        this.PATRONYMIC = PATRONYMIC;
        this.NAME = NAME;
        this.PHONE = PHONE;
        this.email = email;
        this.PASSPORTSERIES = PASSPORTSERIES;
        this.PASSPORTID = PASSPORTID;
        this.creditAmount = creditAmount;
        this.creditTerm = creditTerm;
        this.entryInterestRate = entryInterestRate;
    }

    public Clients() {

    }

    public String getSURNAME() {
        return SURNAME;
    }

    public void setSURNAME(String SURNAME) {
        this.SURNAME = SURNAME;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getPATRONYMIC() {
        return PATRONYMIC;
    }

    public void setPATRONYMIC(String PATRONYMIC) {
        this.PATRONYMIC = PATRONYMIC;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public int getPASSPORTSERIES() {
        return PASSPORTSERIES;
    }

    public void setPASSPORTSERIES(int PASSPORTSERIES) {
        this.PASSPORTSERIES = PASSPORTSERIES;
    }

    public int getPASSPORTID() {
        return PASSPORTID;
    }

    public void setPASSPORTID(int PASSPORTID) {
        this.PASSPORTID = PASSPORTID;
    }

    public Double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(Double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public Double getEntryInterestRate() {
        return entryInterestRate;
    }

    public void setEntryInterestRate(Double entryIterestRate) {
        this.entryInterestRate = entryIterestRate;
    }

    public Double getCreditTerm() {
        return creditTerm;
    }

    public void setCreditTerm(Double creditTerm) {
        this.creditTerm = creditTerm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Clients clone() {
        try {
            return (Clients)super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
